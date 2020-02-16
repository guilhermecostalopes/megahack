package br.com.megahack.security.jwt;

import static br.com.ghsistemas.principal.utilitario.mensagem.TipoMensagemEnum.ERROR;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import br.com.ghsistemas.principal.utilitario.mensagem.Mensagem;
import br.com.megahack.exception.AutenticacaoException;
import lombok.extern.apachecommons.CommonsLog;

@Component
@CommonsLog
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 7127844194911897823L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException autenticacaoExcecao) throws IOException, ServletException {
		try {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		} catch (UsernameNotFoundException e) {
			log.error(e.getMessage(), e);
			Collection<Mensagem> mensagens = new ArrayList<>();
			mensagens.add(new Mensagem(ERROR, "erro-autenticacao", e.getMessage()));
			throw new AutenticacaoException(mensagens);
		}
	}
}