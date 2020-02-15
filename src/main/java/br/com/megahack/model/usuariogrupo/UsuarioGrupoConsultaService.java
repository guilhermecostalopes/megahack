package br.com.megahack.model.usuariogrupo;

import java.util.Collection;

import br.com.megahack.model.usuario.Usuario;

public interface UsuarioGrupoConsultaService {

	Collection<UsuarioGrupo> buscarUsuarioGrupoPorUsuario(Usuario usuario);
}
