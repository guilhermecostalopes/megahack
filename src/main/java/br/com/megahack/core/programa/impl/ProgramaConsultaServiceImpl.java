package br.com.megahack.core.programa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programa.ProgramaConsultaService;

@Service
public class ProgramaConsultaServiceImpl implements ProgramaConsultaService {

	@Autowired
	private ProgramaRepository repository;

	@Override
	public Programa buscarPorCodigo(String codigo) {
		return repository.findByCodigo(codigo);
	}
}
