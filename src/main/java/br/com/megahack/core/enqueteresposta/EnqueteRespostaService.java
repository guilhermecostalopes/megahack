package br.com.megahack.core.enqueteresposta;

import br.com.megahack.core.enquete.resource.EnqueteResource;

public interface EnqueteRespostaService {

	EnqueteResource votarFavor(String codigoRepostaEnquete, String codigoEnquete, String codPrograma, Integer dia,
			Integer mes, Integer ano);

	EnqueteResource votarContra(String codigoRepostaEnquete, String codigoEnquete, String codPrograma, Integer dia,
			Integer mes, Integer ano);
}
