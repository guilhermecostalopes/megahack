package br.com.megahack.core.regiao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.regiao.Regiao;
import br.com.megahack.core.regiao.RegiaoService;
import br.com.megahack.core.regiao.resource.RegiaoResource;

@Service
public class RegiaoServiceImpl implements RegiaoService {

	@Autowired
	private RegiaoRepository repository;

	@Override
	public RegiaoResource incluir(RegiaoResource resource) {
		Regiao entidade = repository.save(Regiao.builder().nome(resource.getNome()).build());
		alterarResource(resource, entidade);
		return resource;
	}

	private void alterarResource(RegiaoResource resource, Regiao entidade) {
		resource.setNome(entidade.getNome());
	}
}
