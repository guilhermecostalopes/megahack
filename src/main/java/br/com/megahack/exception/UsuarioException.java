package br.com.megahack.exception;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = 48243678275118344L;

	private Collection<String> mensagens;

	public UsuarioException(Collection<String> mensagens) {
		super();
		this.mensagens = mensagens;
	}
}
