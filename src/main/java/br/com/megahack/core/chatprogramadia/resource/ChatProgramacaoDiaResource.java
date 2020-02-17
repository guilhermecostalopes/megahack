package br.com.megahack.core.chatprogramadia.resource;

import static lombok.AccessLevel.PRIVATE;

import br.com.ghsistemas.principal.core.resorce.PrincipalEnvioResource;
import br.com.megahack.core.programa.resource.ProgramaResource;
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
public class ChatProgramacaoDiaResource extends PrincipalEnvioResource {

	private String usuario;
	private String texto;
	private ProgramaResource programaResource;
}
