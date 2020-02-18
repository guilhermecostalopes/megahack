package br.com.megahack.core.programadia.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;

@Service
public class ProgramaDiaConsultaServiceImpl implements ProgramaDiaConsultaService {

	@Autowired
	private ProgramaDiaRepository repository;

	@Override
	public ProgramaDia buscarPorId(String idPrograma) {
		return repository.findById(idPrograma).orElse(null);
	}

	@Override
	public Collection<ProgramaDia> buscarTodos() {
		return (Collection<ProgramaDia>) repository.findAll();
	}
}
