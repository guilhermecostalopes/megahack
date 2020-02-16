package br.com.megahack.rest.usuario;

import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.ALTERAR;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.ALTERAR_CHAVE;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.BUSCAR;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.ERRO_EM;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.EXCLAMACAO;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.EXCLUIR;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.EXCLUIR_CHAVE;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.INCLUIR;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.INCLUIR_CHAVE;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.PESQUISAR;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.PESQUISAR_CHAVE;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.POR_ID;
import static br.com.ghsistemas.principal.utilitario.constantes.ConstantesUtil.POR_ID_CHAVE;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ghsistemas.principal.utilitario.mensagem.Mensagem;
import br.com.megahack.exception.UsuarioException;
import br.com.megahack.rest.MegaHackController;
import br.com.megahack.core.usuario.UsuarioConsultaService;
import br.com.megahack.core.usuario.UsuarioService;
import br.com.megahack.core.usuario.resource.UsuarioFiltroResource;
import br.com.megahack.core.usuario.resource.UsuarioPaginacaoResource;
import br.com.megahack.core.usuario.resource.UsuarioResource;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController extends MegaHackController {

	private static final String USUARIO_CHAVE = "usuario-";
	private static final String ERRO_USUARIO_CHAVE = "erro-" + USUARIO_CHAVE;
	private static final String USUARIO = " usuário ";
	@Autowired
	private UsuarioService service;
	@Autowired
	private UsuarioConsultaService consultaService;

	@PostMapping(value = "/incluir", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = CREATED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> incluir(@RequestBody UsuarioResource resource) throws UsuarioException {
		try {
			resource.setLoginUsuario(retornLoginContexto().getUsername());
			UsuarioResource incluir = service.incluir(resource);
			return new ResponseEntity<>(incluir, OK);
		} catch (UsuarioException e) {
			return excecaoSistema(e);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_USUARIO_CHAVE + INCLUIR_CHAVE, ERRO_EM + INCLUIR + USUARIO + EXCLAMACAO);
		}
	}

	@PutMapping(value = "/alterar", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = ACCEPTED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> alterar(@RequestBody UsuarioResource resource) throws UsuarioException {
		try {
			resource.setLoginUsuario(retornLoginContexto().getUsername());
			UsuarioResource alterar = service.alterar(resource);
			return new ResponseEntity<>(alterar, OK);
		} catch (UsuarioException e) {
			return excecaoSistema(e);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_USUARIO_CHAVE + ALTERAR_CHAVE, ERRO_EM + ALTERAR + USUARIO + EXCLAMACAO);
		}
	}

	@DeleteMapping(value = "/excluir/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = ACCEPTED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> exclusao(@PathVariable("id") String id) throws UsuarioException {
		try {
			UsuarioResource excluir = service.excluir(preencherResource(id));
			return new ResponseEntity<>(excluir, OK);
		} catch (UsuarioException e) {
			return excecaoSistema(e);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_USUARIO_CHAVE + EXCLUIR_CHAVE, ERRO_EM + EXCLUIR + USUARIO + EXCLAMACAO);
		}
	}

	@GetMapping(value = "/pesquisar", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = ACCEPTED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
	public ResponseEntity<?> buscarPaginacao(UsuarioFiltroResource filtro) throws UsuarioException {
		try {
			UsuarioPaginacaoResource pesquisa = consultaService.buscarSistemaPaginacao(filtro);
			return new ResponseEntity<>(pesquisa, OK);
		} catch (UsuarioException e) {
			return excecaoSistema(e);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_USUARIO_CHAVE + PESQUISAR_CHAVE, ERRO_EM + PESQUISAR + USUARIO + EXCLAMACAO);
		}
	}

	@GetMapping(value = "/buscar/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = ACCEPTED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> buscarPeloId(@PathVariable("id") String id) throws UsuarioException {
		try {
			UsuarioResource resource = consultaService.buscarPeloId(preencherResource(id));
			return new ResponseEntity<>(resource, OK);
		} catch (UsuarioException e) {
			return excecaoSistema(e);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_USUARIO_CHAVE + POR_ID_CHAVE, ERRO_EM + BUSCAR + USUARIO + POR_ID + EXCLAMACAO);
		}
	}

	private UsuarioResource preencherResource(String id) {
		UsuarioResource resource = UsuarioResource.builder().build();
		resource.setLoginUsuario(retornLoginContexto().getUsername());
		resource.setId(id);
		return resource;
	}

	private ResponseEntity<?> excecaoSistema(UsuarioException e) {
		log.error(e.getMessage(), e);
		Collection<Mensagem> mensagems = mensagemSalvar(true);
		mensagems.addAll(e.getMensagens());
		return new ResponseEntity<>(mensagems, CONFLICT);
	}
}
