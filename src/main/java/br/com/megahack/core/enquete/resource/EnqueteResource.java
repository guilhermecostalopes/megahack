package br.com.megahack.core.enquete.resource;

import static lombok.AccessLevel.PRIVATE;

import java.util.Collection;

import br.com.megahack.core.enqueteresposta.resource.EnqueteRespostaResource;
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
public class EnqueteResource {

	private ProgramaDiaResource programaDiaResource;
	private String pergunta;
	// Deve vir no formato DD/MM/YYYY_HH:MM:SS
	private String dataHoraInicio;
	// Deve vir no formato DD/MM/YYYY_HH:MM:SS
	private String dataHoraFim;
	private Collection<EnqueteRespostaResource> respostas;
}
