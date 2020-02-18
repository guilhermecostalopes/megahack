package br.com.megahack.core.programadia.impl;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.enuns.DiaSemanaEnum;
import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.regiao.Regiao;

@Repository
interface ProgramaDiaRepository extends CrudRepository<ProgramaDia, String> {

	ProgramaDia findByProgramaAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFim(Programa programa, Regiao regiao,
			DiaSemanaEnum diaSemana, Date horaInicio, Date horaFim);
}
