package br.com.megahack.core.programadia.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.programadia.ProgramaDia;

@Repository
interface ProgramaDiaRepository extends CrudRepository<ProgramaDia, String> {

}
