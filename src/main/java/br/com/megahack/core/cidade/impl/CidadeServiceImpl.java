package br.com.megahack.core.cidade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.cidade.Cidade;
import br.com.megahack.core.cidade.CidadeService;
import br.com.megahack.core.cidade.resource.CidadeResource;
import br.com.megahack.core.estado.Estado;
import br.com.megahack.core.estado.EstadoConsultaService;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository repository;
	@Autowired
	private EstadoConsultaService estadoConsultaService;

	@Override
	public CidadeResource incluir(CidadeResource resource) {
		Estado estado = estadoConsultaService.buscarPorNome(resource.getEstado());
		Cidade entidade = repository.save(Cidade.builder().nome(resource.getNome()).estado(estado).build());
		alterarResource(resource, entidade);
		return resource;
	}

	private void alterarResource(CidadeResource resource, Cidade entidade) {
		resource.setEstado(entidade.getEstado().getNome());
		resource.setNome(entidade.getNome());
	}
}
