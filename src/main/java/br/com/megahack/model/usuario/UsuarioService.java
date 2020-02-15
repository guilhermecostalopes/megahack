package br.com.megahack.model.usuario;

import br.com.megahack.model.usuario.resource.UsuarioResource;

public interface UsuarioService {

	UsuarioResource incluir(UsuarioResource resource);
	
	UsuarioResource alterar(UsuarioResource resource);

	UsuarioResource excluir(UsuarioResource resource);
}
