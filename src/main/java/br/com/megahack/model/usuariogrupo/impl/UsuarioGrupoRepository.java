package br.com.megahack.model.usuariogrupo.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.usuariogrupo.UsuarioGrupo;

@Repository
interface UsuarioGrupoRepository extends CrudRepository<UsuarioGrupo, String> {

}
