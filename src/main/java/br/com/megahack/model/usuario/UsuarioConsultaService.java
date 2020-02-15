package br.com.megahack.model.usuario;

import br.com.megahack.model.usuario.resource.UsuarioFiltroResource;
import br.com.megahack.model.usuario.resource.UsuarioPaginacaoResource;
import br.com.megahack.model.usuario.resource.UsuarioResource;

public interface UsuarioConsultaService {

	Usuario buscarUsuarioPorLogin(String login);

	UsuarioPaginacaoResource buscarSistemaPaginacao(UsuarioFiltroResource filtro);

	UsuarioResource buscarPeloId(UsuarioResource preencherResource);
}
