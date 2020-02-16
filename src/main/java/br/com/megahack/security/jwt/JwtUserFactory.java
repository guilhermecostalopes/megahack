package br.com.megahack.security.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.megahack.core.usuario.Usuario;
import br.com.megahack.core.usuariogrupo.UsuarioGrupo;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser criarUsuario(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getLogin(), usuario.getSenha(),
				autorizacaoParaUsuario(usuario.getUsuariosGrupos()));
	}

	private static List<GrantedAuthority> autorizacaoParaUsuario(Collection<UsuarioGrupo> usuariosGrupos) {
		List<GrantedAuthority> autorizacoes = new ArrayList<>();
		usuariosGrupos.forEach(
				usuarioGrupo -> autorizacoes.add(new SimpleGrantedAuthority(usuarioGrupo.getGrupo().getRole())));
		return autorizacoes;
	}
}
