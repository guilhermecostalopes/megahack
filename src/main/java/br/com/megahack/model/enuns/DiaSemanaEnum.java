package br.com.megahack.model.enuns;

import lombok.Getter;

/**
 * Entidade de programação por emissora
 * 
 * @author megahack
 */
@Getter
public enum DiaSemanaEnum {

	DOMINGO("Domingo"), SEGUNDA("Segunda feira"), TERCA("Terça feira"), QUARTA("Quarta feira"), QUINTA("Quinta feira"),
	SEXTA("Sexta feira"), SABADO("Sábado");

	private String descricao;

	DiaSemanaEnum(String descricao) {
		this.descricao = descricao;
	}

	public static DiaSemanaEnum buscaTipoEnum(String descricao) {
		for (DiaSemanaEnum enun : DiaSemanaEnum.values()) {
			if (enun.name().equals(descricao)) {
				return enun;
			}
		}
		return null;
	}
}
