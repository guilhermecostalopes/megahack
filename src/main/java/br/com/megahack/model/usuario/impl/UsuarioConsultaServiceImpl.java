package br.com.megahack.model.usuario.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.model.usuario.Usuario;
import br.com.megahack.model.usuario.UsuarioConsultaService;

@Service
public class UsuarioConsultaServiceImpl implements UsuarioConsultaService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario buscarUsuarioPorLogin(String login) {
		return repository.findByLogin(login);
	}
}
