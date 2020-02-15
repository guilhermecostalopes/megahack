package br.com.megahack.model.pessoa.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.pessoa.Pessoa;

@Repository
interface PessoaRepository extends CrudRepository<Pessoa, String> {

}
