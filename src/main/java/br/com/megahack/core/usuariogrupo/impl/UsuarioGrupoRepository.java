package br.com.megahack.core.usuariogrupo.impl;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.usuario.Usuario;
import br.com.megahack.core.usuariogrupo.UsuarioGrupo;

@Repository
interface UsuarioGrupoRepository extends CrudRepository<UsuarioGrupo, String> {

	Collection<UsuarioGrupo> findByUsuario(Usuario usuario);
}
