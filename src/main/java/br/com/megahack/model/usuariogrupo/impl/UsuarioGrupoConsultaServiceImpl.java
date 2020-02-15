package br.com.megahack.model.usuariogrupo.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.model.usuario.Usuario;
import br.com.megahack.model.usuariogrupo.UsuarioGrupo;
import br.com.megahack.model.usuariogrupo.UsuarioGrupoConsultaService;

@Service
public class UsuarioGrupoConsultaServiceImpl implements UsuarioGrupoConsultaService {

	@Autowired
	private UsuarioGrupoRepository repository;

	@Override
	public Collection<UsuarioGrupo> buscarUsuarioGrupoPorUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}
}
