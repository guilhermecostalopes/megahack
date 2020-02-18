package br.com.megahack.core.enqueteresposta.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.enquete.Enquete;
import br.com.megahack.core.enqueteresposta.EnqueteResposta;

@Repository
interface EnqueteRespostaRepository extends CrudRepository<EnqueteResposta, String> {

	EnqueteResposta findByCodigoAndEnquete(String codigo, Enquete enquete);
}
