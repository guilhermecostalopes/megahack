package br.com.megahack.model.usuariogrupo.impl;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.usuario.Usuario;
import br.com.megahack.model.usuariogrupo.UsuarioGrupo;

@Repository
interface UsuarioGrupoRepository extends CrudRepository<UsuarioGrupo, String> {

	Collection<UsuarioGrupo> findByUsuario(Usuario usuario);
}
