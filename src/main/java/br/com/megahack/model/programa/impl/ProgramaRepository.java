package br.com.megahack.model.programa.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.programa.Programa;

@Repository
interface ProgramaRepository extends CrudRepository<Programa, String> {

}
