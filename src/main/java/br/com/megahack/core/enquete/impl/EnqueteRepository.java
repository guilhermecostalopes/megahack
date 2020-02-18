package br.com.megahack.core.enquete.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.enquete.Enquete;

@Repository
interface EnqueteRepository extends CrudRepository<Enquete, String> {

}
