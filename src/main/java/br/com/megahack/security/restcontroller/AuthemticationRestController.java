package br.com.megahack.security.restcontroller;

import static br.com.ghsistemas.principal.utilitario.mensagem.TipoMensagemEnum.SUCCESS;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ghsistemas.principal.utilitario.mensagem.Mensagem;
import br.com.megahack.core.usuario.Usuario;
import br.com.megahack.core.usuario.UsuarioConsultaService;
import br.com.megahack.core.usuariogrupo.UsuarioGrupo;
import br.com.megahack.security.jwt.JwtAuthemticationRequest;
import br.com.megahack.security.jwt.JwtTokenUtil;
import br.com.megahack.security.model.CurrrentUser;

@RestController
@CrossOrigin(origins = "*")
public class AuthemticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UsuarioConsultaService usuarioConsultaService;

	@PostMapping(value = "/api/auth")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> criaAutenticacaoToken(@RequestBody JwtAuthemticationRequest authemticationRequest)
			throws UsernameNotFoundException {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authemticationRequest.getLogin(), authemticationRequest.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = userDetailsService.loadUserByUsername(authemticationRequest.getLogin());
		String token = jwtTokenUtil.gerarToken(userDetails);
		Usuario usuario = usuarioConsultaService.buscarUsuarioPorLogin(authemticationRequest.getLogin());
		Collection<String> autorizacoes = new ArrayList<>();
		retornaAutorizacoesUsuarios(usuario.getUsuariosGrupos(), autorizacoes);
		return ResponseEntity.ok(CurrrentUser.builder().token(token).login(usuario.getLogin())
				.autorizacoes(autorizacoes).nome(usuario.getPessoa().getNome())
				.mensagem(new Mensagem(SUCCESS, "usuario-logado-com-sucesso", "Usuário logado com sucesso !")).build());
	}

	@PostMapping(value = "/api/auth/refresh")
	public ResponseEntity<?> atualizarTokcn(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String usuario = jwtTokenUtil.usuarioNomeFromToken(token);
		Usuario usuarioBanco = usuarioConsultaService.buscarUsuarioPorLogin(usuario);
		if (jwtTokenUtil.tokenPodeSerAtualizado(token)) {
			String tokenAtualizado = jwtTokenUtil.atualizaToken(token);
			Collection<String> autorizacoes = new ArrayList<>();
			retornaAutorizacoesUsuarios(usuarioBanco.getUsuariosGrupos(), autorizacoes);
			return ResponseEntity.ok(CurrrentUser.builder().token(tokenAtualizado).login(usuarioBanco.getLogin())
					.nome(usuarioBanco.getPessoa().getNome()).autorizacoes(autorizacoes).build());
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	private void retornaAutorizacoesUsuarios(Collection<UsuarioGrupo> usuariosGrupos, Collection<String> autorizacoes) {
		usuariosGrupos.forEach(usuarioGrupoSistema -> autorizacoes.add(usuarioGrupoSistema.getGrupo().getRole()));
	}
}
