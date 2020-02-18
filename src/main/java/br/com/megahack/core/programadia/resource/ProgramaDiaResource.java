package br.com.megahack.core.programadia.resource;

import static lombok.AccessLevel.PUBLIC;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC)
public class ProgramaDiaResource {

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
