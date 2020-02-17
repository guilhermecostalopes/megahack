package br.com.megahack.core.programa.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.programa.Programa;

@Repository
interface ProgramaRepository extends CrudRepository<Programa, String> {

	Programa findByNome(String nome);
}
