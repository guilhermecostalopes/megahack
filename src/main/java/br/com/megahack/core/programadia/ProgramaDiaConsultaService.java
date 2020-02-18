package br.com.megahack.core.programadia;

import br.com.megahack.core.programa.resource.ProgramaResourceAAAA;

public interface ProgramaDiaConsultaService {

	ProgramaDia buscarPorProgramacaoAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFim(ProgramaResourceAAAA resource);
}
