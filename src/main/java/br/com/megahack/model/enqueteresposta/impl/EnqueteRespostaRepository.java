package br.com.megahack.model.enqueteresposta.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.enqueteresposta.EnqueteResposta;

@Repository
interface EnqueteRespostaRepository extends CrudRepository<EnqueteResposta, String> {

}
