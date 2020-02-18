package br.com.megahack.core.chatprogramadia;

import java.util.Collection;

public interface ChatProgramacaoDiaConsultaService {

	Collection<ChatProgramacaoDia> buscarPorProgramaAndDiaAndMesAndAno(String codPrograma, Integer dia, Integer mes,
			Integer ano);
}
