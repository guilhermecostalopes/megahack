package br.com.megahack.core.enquete;

import br.com.megahack.core.enquete.resource.EnqueteResource;

public interface EnqueteConsultaService {

	EnqueteResource buscarPorProgramaAndDiaAndMesAndAno(String codPrograma, Integer dia, Integer mes, Integer ano);
}
