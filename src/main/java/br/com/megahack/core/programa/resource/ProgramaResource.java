package br.com.megahack.core.programa.resource;

import static lombok.AccessLevel.PRIVATE;

import br.com.ghsistemas.principal.core.resorce.PrincipalEnvioResource;
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
public class ProgramaResource extends PrincipalEnvioResource {

	private String nome;
	private String descricao;
	private Integer faixaEtaria;
	private Integer avaliacaoPrograma;
}
