package br.com.megahack.core.programa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.programa.ProgramaService;

@Service
public class ProgramaServiceImpl implements ProgramaService {

	@Autowired
	private ProgramaRepository repository;
}
