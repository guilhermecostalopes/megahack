package br.com.megahack.model.enquete.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.enquete.Enquete;

@Repository
interface EnqueteRepository extends CrudRepository<Enquete, String> {

}
