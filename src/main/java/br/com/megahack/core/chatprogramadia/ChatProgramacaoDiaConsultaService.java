package br.com.megahack.core.chatprogramadia;

import java.util.Collection;

import br.com.megahack.core.programa.resource.ProgramaResourceAAAA;

public interface ChatProgramacaoDiaConsultaService {

	Collection<ChatProgramacaoDia> buscarPorProgramacaoDia(ProgramaResourceAAAA resource);
}
