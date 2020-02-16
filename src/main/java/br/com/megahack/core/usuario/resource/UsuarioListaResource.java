package br.com.megahack.core.usuario.resource;

import static lombok.AccessLevel.PRIVATE;

import br.com.ghsistemas.principal.core.resorce.PrincipalResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class UsuarioListaResource extends PrincipalResource {

	private String id;
	private String login;
	private String senha;
	private byte[] avatar;
}
