package br.com.megahack.core.estado.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.estado.Estado;
import br.com.megahack.core.estado.EstadoService;
import br.com.megahack.core.estado.resource.EstadoResource;
import br.com.megahack.core.regiao.Regiao;
import br.com.megahack.core.regiao.RegiaoConsultaService;

@Service
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoRepository repository;
	@Autowired
	private RegiaoConsultaService regiaoConsultaService;

	@Override
	public EstadoResource incluir(EstadoResource resource) {
		Regiao regiao = regiaoConsultaService.buscarPorNome(resource.getRegiao());
		Estado entidade = repository.save(Estado.builder().nome(resource.getNome()).regiao(regiao).build());
		alterarResource(resource, entidade);
		return resource;
	}

	private void alterarResource(EstadoResource resource, Estado entidade) {
		resource.setNome(entidade.getNome());
		resource.setRegiao(entidade.getRegiao().getNome());
	}
}
