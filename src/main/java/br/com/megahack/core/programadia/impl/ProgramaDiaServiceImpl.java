package br.com.megahack.core.programadia.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.programadia.ProgramaDiaService;

@Service
public class ProgramaDiaServiceImpl implements ProgramaDiaService {

	@Autowired
	private ProgramaDiaRepository repository;
}
