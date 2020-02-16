package br.com.megahack.security.jwt;

import static lombok.AccessLevel.PROTECTED;

import java.io.Serializable;

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
public class JwtAuthemticationRequest implements Serializable {

	private static final long serialVersionUID = 5753621220900845021L;

	private String login;
	private String senha;
}
