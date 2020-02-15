package br.com.megahack.model.emissora.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.emissora.Emissora;

@Repository
interface EmissoriaRepository extends CrudRepository<Emissora, String> {

}
