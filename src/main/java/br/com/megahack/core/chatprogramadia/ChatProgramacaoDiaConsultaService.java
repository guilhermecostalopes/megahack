package br.com.megahack.core.chatprogramadia;

import java.util.Collection;

import br.com.megahack.core.programa.resource.ProgramaResource;

public interface ChatProgramacaoDiaConsultaService {

	Collection<ChatProgramacaoDia> buscarPorProgramacaoDia(ProgramaResource resource);
}
