package br.com.megahack.core.enquete;

import br.com.megahack.core.enquete.resource.EnqueteResource;

public interface EnqueteConsultaService {

	Enquete buscaPorCodigo(String codigo);

	EnqueteResource buscarPorProgramaAndDiaAndMesAndAno(String codPrograma, Integer dia, Integer mes, Integer ano);
}
