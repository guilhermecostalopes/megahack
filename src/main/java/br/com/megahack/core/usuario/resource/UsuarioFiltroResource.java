package br.com.megahack.core.usuario.resource;

import br.com.ghsistemas.principal.core.resorce.PaginacaoEnvioResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class UsuarioFiltroResource extends PaginacaoEnvioResource {

	private String login;
}
