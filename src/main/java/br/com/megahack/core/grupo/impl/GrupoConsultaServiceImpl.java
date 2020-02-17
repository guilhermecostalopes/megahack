package br.com.megahack.core.grupo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.grupo.Grupo;
import br.com.megahack.core.grupo.GrupoConsultaService;

@Service
public class GrupoConsultaServiceImpl implements GrupoConsultaService {

	@Autowired
	private GrupoRepository repository;

	@Override
	public Grupo buscarPorNome(String nome) {
		return repository.findByNome(nome);
	}
}
