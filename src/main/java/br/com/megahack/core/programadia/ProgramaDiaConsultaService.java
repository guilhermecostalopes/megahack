package br.com.megahack.core.programadia;

import java.util.Collection;

import br.com.megahack.core.programa.Programa;

public interface ProgramaDiaConsultaService {

	ProgramaDia buscarPorProgramaDiaMesAno(Programa programa, Integer dia, Integer mes, Integer ano);

	Collection<ProgramaDia> buscarTodos();
}
