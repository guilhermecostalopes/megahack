package br.com.megahack.core.chatprogramadia.resource;

import static lombok.AccessLevel.PRIVATE;

import br.com.megahack.core.programadia.resource.ProgramaDiaResource;
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
public class ChatProgramacaoDiaResource {

	private String codPrograma;
	private String usuario;
	private String texto;
	private ProgramaDiaResource programaResource;
}
