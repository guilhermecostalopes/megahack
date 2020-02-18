package br.com.megahack.core.usuario.resource;

import static lombok.AccessLevel.PRIVATE;

import java.util.Collection;

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
public class UsuarioPaginacaoResource {

	private Collection<UsuarioListaResource> lista;
}
