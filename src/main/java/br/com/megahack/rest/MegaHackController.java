package br.com.megahack.rest;

import static org.springframework.http.HttpStatus.CONFLICT;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.megahack.security.jwt.JwtUser;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public abstract class MegaHackController {

	protected JwtUser retornLoginContexto() {
		SecurityContext s = retornaContextoSegurancaSpring();
		return (JwtUser) s.getAuthentication().getPrincipal();
	}

	protected ResponseEntity<?> excecaoGeral(Exception e, String chave, String texto) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(texto, CONFLICT);
	}

	private SecurityContext retornaContextoSegurancaSpring() {
		return SecurityContextHolder.getContext();
	}

}
