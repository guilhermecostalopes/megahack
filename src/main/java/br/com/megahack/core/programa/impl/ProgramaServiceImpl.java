package br.com.megahack.core.programa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programa.ProgramaService;
import br.com.megahack.core.programa.resource.ProgramaResource;

@Service
public class ProgramaServiceImpl implements ProgramaService {

	@Autowired
	private ProgramaRepository repository;

	@Override
	public ProgramaResource incluir(ProgramaResource resource) {
		Programa entidade = repository.save(Programa.builder().avaliacaoPrograma(resource.getAvaliacaoPrograma())
				.codigo(resource.getCodigo()).descricao(resource.getDescricao()).faixaEtaria(resource.getFaixaEtaria())
				.nome(resource.getNome()).build());
		alterarResource(resource, entidade);
		return resource;
	}

	private void alterarResource(ProgramaResource resource, Programa entidade) {
		resource.setAvaliacaoPrograma(entidade.getAvaliacaoPrograma());
		resource.setDescricao(entidade.getDescricao());
		resource.setFaixaEtaria(entidade.getFaixaEtaria());
		resource.setNome(entidade.getNome());
	}
}
