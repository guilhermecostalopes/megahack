package br.com.megahack.model.cidade.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.cidade.Cidade;

@Repository
interface CidadeRepository extends CrudRepository<Cidade, String> {

}
