package br.com.megahack.security.jwt;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -5422914969967914437L;

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jxt.expiration}")
	private Long expiration;

	public String usuarioNomeFromToken(String token) {
		String usuario;
		try {
			Claims claims = claimsFromToken(token);
			usuario = claims.getSubject();
		} catch (Exception e) {
			usuario = null;
		}
		return usuario;
	}

	public Date expiracaoDataFromToken(String token) {
		Date expiracao;
		try {
			Claims claims = claimsFromToken(token);
			expiracao = claims.getExpiration();
		} catch (Exception e) {
			expiracao = null;
		}
		return expiracao;
	}

	public String gerarToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", userDetails.getUsername());
		claims.put("created", new Date());
		return geracaoToken(claims);
	}

	public Boolean tokenPodeSerAtualizado(String token) {
		return (!tokenEstaExpirado(token));
	}

	public String atualizaToken(String token) {
		String tokenAtualizado;
		try {
			Claims claims = claimsFromToken(token);
			claims.put("created", new Date());
			tokenAtualizado = geracaoToken(claims);
		} catch (Exception e) {
			tokenAtualizado = null;
		}
		return tokenAtualizado;
	}

	public Boolean tokenValido(String token, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		String usuario = usuarioNomeFromToken(token);
		return (usuario.equals(user.getUsername()) && !tokenEstaExpirado(token));
	}

	private String geracaoToken(Map<String, Object> claims) {
		Date dataCriacao = (Date) claims.get("created");
		Date dataExpiracao = new Date(dataCriacao.getTime() + expiration * 1000);
		return Jwts.builder().setClaims(claims).setExpiration(dataExpiracao).signWith(HS512, secret).compact();
	}

	private Claims claimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Boolean tokenEstaExpirado(String token) {
		Date expiracao = expiracaoDataFromToken(token);
		return expiracao.before(new Date());
	}
}
