package br.com.megahack.core.programadia.impl;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;

@Service
public class ProgramaDiaConsultaServiceImpl implements ProgramaDiaConsultaService {

	@Autowired
	private ProgramaDiaRepository repository;

	@Override
	public ProgramaDia buscarPorProgramaDiaMesAno(Programa programa, Integer dia, Integer mes, Integer ano) {
		GregorianCalendar calendar = new GregorianCalendar(ano, mes, dia);
		Date d = calendar.getTime();
		return repository.findByProgramaAndData(programa, d);
	}

	@Override
	public Collection<ProgramaDia> buscarTodos() {
		return (Collection<ProgramaDia>) repository.findAll();
	}
}
