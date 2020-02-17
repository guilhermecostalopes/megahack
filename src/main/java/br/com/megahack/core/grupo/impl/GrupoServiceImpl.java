package br.com.megahack.core.grupo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.grupo.Grupo;
import br.com.megahack.core.grupo.GrupoService;
import br.com.megahack.core.grupo.resource.GrupoResource;

@Service
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	private GrupoRepository repository;

	@Override
	public GrupoResource incluir(GrupoResource resource) {
		Grupo grupo = repository.save(Grupo.builder().nome(resource.getNome()).role(resource.getRole()).build());
		alterarResource(resource, grupo);
		return resource;
	}

	private void alterarResource(GrupoResource resource, Grupo grupo) {
		resource.setNome(resource.getNome());
		resource.setRole(resource.getRole());
	}
}
