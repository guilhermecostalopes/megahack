package br.com.megahack.core.usuario;

import br.com.megahack.core.usuario.resource.UsuarioResource;
	
public interface UsuarioConsultaService {

	Usuario buscarUsuarioPorLogin(String login);

	UsuarioResource buscarPeloId(UsuarioResource preencherResource);

}
