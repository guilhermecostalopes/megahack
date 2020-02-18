package br.com.megahack.core.usuario.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.usuario.Usuario;
import br.com.megahack.core.usuario.UsuarioConsultaService;
import br.com.megahack.core.usuario.resource.UsuarioResource;

@Service
public class UsuarioConsultaServiceImpl implements UsuarioConsultaService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario buscarUsuarioPorLogin(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public UsuarioResource buscarPeloId(UsuarioResource resource) {
		Usuario usuario = repository.findById(resource.getId()).orElse(null);
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
