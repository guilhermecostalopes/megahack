package br.com.megahack.core.regiao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.regiao.Regiao;
import br.com.megahack.core.regiao.RegiaoConsultaService;

@Service
public class RegiaoConsultaServiceImpl implements RegiaoConsultaService {

	@Autowired
	private RegiaoRepository repository;

	@Override
	public Regiao buscarPorNome(String nome) {
		return repository.findByNome(nome);
	}
}
