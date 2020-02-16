package br.com.megahack.core.regiao.resource;

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
public class RegiaoResource extends PrincipalEnvioResource {

	private String nome;
}
