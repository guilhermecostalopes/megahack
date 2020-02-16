package br.com.megahack.core.usuario;

import br.com.megahack.core.usuario.resource.UsuarioFiltroResource;
import br.com.megahack.core.usuario.resource.UsuarioPaginacaoResource;
import br.com.megahack.core.usuario.resource.UsuarioResource;

public interface UsuarioConsultaService {

	Usuario buscarUsuarioPorLogin(String login);

	UsuarioPaginacaoResource buscarSistemaPaginacao(UsuarioFiltroResource filtro);

	UsuarioResource buscarPeloId(UsuarioResource preencherResource);

}
