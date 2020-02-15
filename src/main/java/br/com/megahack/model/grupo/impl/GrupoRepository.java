package br.com.megahack.model.grupo.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.grupo.Grupo;

@Repository
interface GrupoRepository extends CrudRepository<Grupo, String> {

}
