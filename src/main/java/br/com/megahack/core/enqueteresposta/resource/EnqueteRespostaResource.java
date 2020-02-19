package br.com.megahack.core.enqueteresposta.resource;

import static lombok.AccessLevel.PRIVATE;

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
public class EnqueteRespostaResource {

	private String codigo;
	private String resposta;
	private Integer votacaoFavor;
	private Integer votacaoContra;
}
