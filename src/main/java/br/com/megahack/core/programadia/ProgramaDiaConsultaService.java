package br.com.megahack.core.programadia;

import br.com.megahack.core.programa.resource.ProgramaResource;

public interface ProgramaDiaConsultaService {

	ProgramaDia buscarPorProgramacaoAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFim(ProgramaResource resource);
}
