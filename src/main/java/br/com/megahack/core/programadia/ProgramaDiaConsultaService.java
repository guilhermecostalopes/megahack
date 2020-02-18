package br.com.megahack.core.programadia;

import java.util.Collection;

public interface ProgramaDiaConsultaService {

	ProgramaDia buscarPorId(String idPrograma);

	Collection<ProgramaDia> buscarTodos();
}
