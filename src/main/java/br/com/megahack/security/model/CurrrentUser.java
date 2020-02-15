package br.com.megahack.security.model;

import static lombok.AccessLevel.PROTECTED;

import java.util.Collection;

import br.com.megahack.Mensagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class CurrrentUser {

	private String token;
	private String login;
	private String nome;
	private Collection<String> autorizacoes;
	private Mensagem mensagem;
}
