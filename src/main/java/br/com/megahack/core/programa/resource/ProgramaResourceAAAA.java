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
public class ProgramaResourceAAAA extends PrincipalEnvioResource {

	// para saber a programção do dia
	private String programa;
	private String regiao;
	// Deve ter os mesmos nomes do enum DiaSemanaEnum
	private String diaSemana;
	// Deve vir no formato DD/MM/YYYY
	private String data;
	// Deve vir no formato HH:MM:SS
	private String horaInicio;
	// Deve vir no formato HH:MM:SS
	private String horaFim;
}
