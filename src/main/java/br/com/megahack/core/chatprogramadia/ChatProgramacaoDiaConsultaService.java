package br.com.megahack.core.chatprogramadia;

import java.util.Collection;

import br.com.megahack.core.programadia.resource.ProgramaDiaResource;

public interface ChatProgramacaoDiaConsultaService {

	Collection<ChatProgramacaoDia> buscarPorProgramacaoDia(ProgramaDiaResource resource);
}
