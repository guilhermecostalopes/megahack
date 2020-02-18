package br.com.megahack.core.enquete;

import br.com.megahack.core.enquete.resource.EnqueteResource;

public interface EnqueteConsultaService {

	EnqueteResource buscarPorProgramaDia(String idPrograma);
}
