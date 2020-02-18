package br.com.megahack.exception;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutenticacaoException extends RuntimeException {

	private static final long serialVersionUID = -2388532433015097185L;

	private Collection<String> mensagens;

	public AutenticacaoException(Collection<String> mensagens) {
		super();
		this.mensagens = mensagens;
	}
}
