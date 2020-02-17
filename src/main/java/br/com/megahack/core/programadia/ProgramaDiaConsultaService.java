package br.com.megahack.core.programadia;

import br.com.megahack.core.chatprogramadia.resource.ChatProgramacaoDiaResource;

public interface ProgramaDiaConsultaService {

	ProgramaDia buscarPorProgramacaoAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFim(ChatProgramacaoDiaResource resource);
}
