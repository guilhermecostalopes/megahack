package br.com.megahack.core.chatprogramadia;

import java.util.Collection;

import br.com.megahack.core.chatprogramadia.resource.ChatProgramacaoDiaResource;

public interface ChatProgramacaoDiaConsultaService {

	Collection<ChatProgramacaoDiaResource> buscarPorProgramaAndDiaAndMesAndAno(String codPrograma, Integer dia,
			Integer mes, Integer ano);
}
