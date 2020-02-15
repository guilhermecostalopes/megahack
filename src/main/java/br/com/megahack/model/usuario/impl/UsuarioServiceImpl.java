package br.com.megahack.model.usuario.impl;

import static br.com.ghsistemas.principal.core.enuns.StatusDoRegistroEnum.EXCLUIDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.megahack.model.usuario.Usuario;
import br.com.megahack.model.usuario.UsuarioService;
import br.com.megahack.model.usuario.resource.UsuarioResource;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UsuarioResource incluir(UsuarioResource resource) {
		String senha = passwordEncoder.encode(resource.getSenha());
		Usuario usuario = repository
				.save(Usuario.builder().login(resource.getLogin()).senha(senha).avatar(resource.getAvatar()).build());
		alterarResource(resource, usuario);
		return resource;
	}

	@Override
	public UsuarioResource alterar(UsuarioResource resource) {
		Usuario usuarioAtual = repository.findById(resource.getId()).orElse(null);
		usuarioAtual.setLogin(resource.getLogin());
		usuarioAtual.setAvatar(resource.getAvatar());
		Usuario usuario = repository.save(usuarioAtual);
		alterarResource(resource, usuario);
		return resource;
	}

	@Override
	public UsuarioResource excluir(UsuarioResource resource) {
		Usuario usuario = repository.findById(resource.getId()).orElse(null);
		usuario.setStatusDoRegistro(EXCLUIDO);
		usuario = repository.save(usuario);
		alterarResource(resource, usuario);
		return resource;
	}

	private void alterarResource(UsuarioResource resource, Usuario usuario) {
		resource.setAvatar(usuario.getAvatar());
		resource.setId(usuario.getId());
		resource.setLogin(usuario.getLogin());
		resource.setSenha(null);
	}
}
