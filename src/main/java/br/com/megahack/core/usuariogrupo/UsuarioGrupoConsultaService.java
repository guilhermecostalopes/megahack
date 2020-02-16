package br.com.megahack.core.usuariogrupo;

import java.util.Collection;

import br.com.megahack.core.usuario.Usuario;

public interface UsuarioGrupoConsultaService {

	Collection<UsuarioGrupo> buscarUsuarioGrupoPorUsuario(Usuario usuario);

}
