package br.com.megahack.core.programadia.impl;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programadia.ProgramaDia;

@Repository
interface ProgramaDiaRepository extends CrudRepository<ProgramaDia, String> {

	ProgramaDia findByProgramaAndData(Programa programa, Date data);
}
