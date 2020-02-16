package br.com.megahack.core.cidade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.cidade.Cidade;
import br.com.megahack.core.cidade.CidadeConsultaService;

@Service
public class CidadeConsultaServiceImpl implements CidadeConsultaService {

	@Autowired
	private CidadeRepository repository;

	@Override
	public Cidade buscarPorNome(String nome) {
		return repository.findByNome(nome);
	}
}
