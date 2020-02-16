package br.com.megahack.core.usuario;

import br.com.megahack.core.usuario.resource.UsuarioResource;

public interface UsuarioService {

	UsuarioResource incluir(UsuarioResource resource);
	
	UsuarioResource alterar(UsuarioResource resource);

	UsuarioResource excluir(UsuarioResource resource);
}
