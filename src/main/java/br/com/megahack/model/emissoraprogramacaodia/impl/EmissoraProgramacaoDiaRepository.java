package br.com.megahack.model.emissoraprogramacaodia.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.emissoraprogramacaodia.EmissoraProgramacaoDia;

@Repository
interface EmissoraProgramacaoDiaRepository extends CrudRepository<EmissoraProgramacaoDia, String> {

}
