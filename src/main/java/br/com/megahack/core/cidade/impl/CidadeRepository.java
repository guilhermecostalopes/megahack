package br.com.megahack.core.cidade.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.cidade.Cidade;

@Repository
interface CidadeRepository extends CrudRepository<Cidade, String> {

	Cidade findByNome(String nome);

}
