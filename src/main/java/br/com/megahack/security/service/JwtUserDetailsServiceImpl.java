package br.com.megahack.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.megahack.core.usuario.Usuario;
import br.com.megahack.core.usuario.UsuarioConsultaService;
import br.com.megahack.core.usuariogrupo.UsuarioGrupoConsultaService;
import br.com.megahack.security.jwt.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioConsultaService usuarioConsultaService;
	@Autowired
	private UsuarioGrupoConsultaService usuarioGrupoConsultaService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioConsultaService.buscarUsuarioPorLogin(login);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário ou senha não existente em nossa base dados !!!");
		} else {
			usuario.setUsuariosGrupos(usuarioGrupoConsultaService.buscarUsuarioGrupoPorUsuario(usuario));
			return JwtUserFactory.criarUsuario(usuario);
		}
	}
}
