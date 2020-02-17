package br.com.megahack.core.grupo.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.grupo.Grupo;

@Repository
interface GrupoRepository extends CrudRepository<Grupo, String> {

	Grupo findByNome(String nome);
}
