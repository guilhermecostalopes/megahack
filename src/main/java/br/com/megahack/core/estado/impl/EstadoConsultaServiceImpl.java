package br.com.megahack.core.estado.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.estado.Estado;
import br.com.megahack.core.estado.EstadoConsultaService;

@Service
public class EstadoConsultaServiceImpl implements EstadoConsultaService {

	@Autowired
	private EstadoRepository repository;

	@Override
	public Estado buscarPorNome(String nome) {
		return repository.findByNome(nome);
	}

	@Override
	public Estado buscarPorAbreviacao(String abreviacao) {
		return repository.findByAbreviacao(abreviacao);
	}
}
