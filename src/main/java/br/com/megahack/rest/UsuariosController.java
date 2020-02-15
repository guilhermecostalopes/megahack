package br.com.megahack.rest;

import static br.com.ghsistemas.principal.utilitario.mensagem.TipoMensagemEnum.ERROR;
import static org.springframework.http.HttpStatus.CONFLICT;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ghsistemas.principal.rest.enuns.MensagensRetorno;
import br.com.ghsistemas.principal.utilitario.mensagem.Mensagem;
import br.com.megahack.security.jwt.JwtUser;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public abstract class UsuariosController extends MensagensRetorno {

	protected JwtUser retornLoginContexto() {
		SecurityContext s = retornaContextoSegurancaSpring();
		return (JwtUser) s.getAuthentication().getPrincipal();
	}

	protected ResponseEntity<?> excecaoGeral(Exception e, String chave, String texto) {
		log.error(e.getMessage(), e);
		Collection<Mensagem> mensagems = mensagemSalvar(true);
		mensagems.add(new Mensagem(ERROR, chave, texto));
		return new ResponseEntity<>(mensagems, CONFLICT);
	}

	private SecurityContext retornaContextoSegurancaSpring() {
		return SecurityContextHolder.getContext();
	}
}
