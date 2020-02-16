package br.com.megahack.exception;

import java.util.Collection;

import br.com.ghsistemas.principal.utilitario.mensagem.Mensagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutenticacaoException extends RuntimeException {

	private static final long serialVersionUID = -2388532433015097185L;

	private Collection<Mensagem> mensagens;

	public AutenticacaoException(Collection<Mensagem> mensagens) {
		super();
		this.mensagens = mensagens;
	}
}
