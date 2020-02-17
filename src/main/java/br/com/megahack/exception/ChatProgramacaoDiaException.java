package br.com.megahack.exception;

import java.util.Collection;

import br.com.ghsistemas.principal.utilitario.mensagem.Mensagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatProgramacaoDiaException extends RuntimeException {

	private static final long serialVersionUID = 48243678275118344L;

	private Collection<Mensagem> mensagens;

	public ChatProgramacaoDiaException(Collection<Mensagem> mensagens) {
		super();
		this.mensagens = mensagens;
	}

}
