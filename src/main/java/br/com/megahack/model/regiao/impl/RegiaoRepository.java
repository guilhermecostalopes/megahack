package br.com.megahack.model.regiao.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.regiao.Regiao;

@Repository
interface RegiaoRepository extends CrudRepository<Regiao, String> {

}
