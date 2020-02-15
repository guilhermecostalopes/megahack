package br.com.megahack.model.estado.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.estado.Estado;

@Repository
interface EstadoRepository extends CrudRepository<Estado, String> {

}
