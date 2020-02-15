package br.com.megahack.model.programadia.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.programadia.ProgramaDia;

@Repository
interface ProgramaDiaRepository extends CrudRepository<ProgramaDia, String> {

}
