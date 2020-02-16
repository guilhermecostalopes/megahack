package br.com.megahack.core.regiao.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.regiao.Regiao;

@Repository
interface RegiaoRepository extends CrudRepository<Regiao, String> {

	Regiao findByNome(String nome);
}
