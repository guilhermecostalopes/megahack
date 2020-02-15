package br.com.megahack;

import static java.text.MessageFormat.format;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = { "chave" })
@EqualsAndHashCode(of = { "chave" })
public class Mensagem implements Comparable<Mensagem>, Serializable {

	private static final long serialVersionUID = -8080905675362815682L;

	private final String texto;
	private final String chave;
	private final TipoMensagemEnum type;

	public Mensagem(TipoMensagemEnum type, String chave, String texto) {
		this.type = type;
		this.chave = chave;
		this.texto = texto;
	}

	public Mensagem(TipoMensagemEnum type, String chave, String pattern, Object... arguments) {
		this(type, chave, format(pattern, arguments));
	}

	@Override
	public int compareTo(Mensagem that) {
		if (that == null) {
			return 1;
		}
		return chave.compareTo(that.chave);
	}
}
