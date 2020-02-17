package br.com.megahack.core.estado.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.estado.Estado;

@Repository
interface EstadoRepository extends CrudRepository<Estado, String> {

	Estado findByNome(String nome);

	Estado findByAbreviacao(String abreviacao);

}
