package br.com.megahack.core.enqueteresposta.resource;

import static lombok.AccessLevel.PRIVATE;

import br.com.ghsistemas.principal.core.resorce.PrincipalEnvioResource;
import br.com.megahack.core.enquete.Enquete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class EnqueteRespostaResource extends PrincipalEnvioResource {

	private Enquete enquete;
	private String resposta;
	private Integer votacaoFavor;
	private Integer votacaoContra;
}
