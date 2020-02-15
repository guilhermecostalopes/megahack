package br.com.megahack.model.usuario.impl;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;
import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.ghsistemas.principal.core.resorce.CampoOrdenadoResource;
import br.com.ghsistemas.principal.core.resorce.PaginacaoRetornoResource;
import br.com.megahack.model.usuario.Usuario;
import br.com.megahack.model.usuario.UsuarioConsultaService;
import br.com.megahack.model.usuario.resource.UsuarioFiltroResource;
import br.com.megahack.model.usuario.resource.UsuarioListaResource;
import br.com.megahack.model.usuario.resource.UsuarioPaginacaoResource;
import br.com.megahack.model.usuario.resource.UsuarioResource;

@Service
public class UsuarioConsultaServiceImpl implements UsuarioConsultaService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario buscarUsuarioPorLogin(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public UsuarioPaginacaoResource buscarSistemaPaginacao(UsuarioFiltroResource filtro) {
		Sort ordenacao = by(filtro.getCampo());
		if (DESC.toString().toUpperCase().equals(filtro.getDirecao().toUpperCase())) {
			ordenacao.descending();
		} else {
			ordenacao.ascending();
		}
		Pageable pages = of(filtro.getPaginaAtual(), filtro.getQuantidadeRegistros(), ordenacao);
		String login = isEmpty(filtro.getLogin()) ? null : "%" + filtro.getLogin().toUpperCase() + "%";
		Page<Usuario> entidades = repository.findByLoginLike(login, pages);
		Collection<UsuarioListaResource> resources = new ArrayList<>();
		entidades.getContent().forEach(entidade -> {
			UsuarioListaResource rsr = UsuarioListaResource.builder().avatar(entidade.getAvatar())
					.login(entidade.getLogin()).build();
			rsr.setStatusDoRegistro(entidade.getStatusDoRegistro().getDescricao());
			if (isNotEmpty(entidade.getId())) {
				rsr.setId(entidade.getId());
			}
			resources.add(rsr);
		});
		CampoOrdenadoResource campoOrdenadoResource = new CampoOrdenadoResource(filtro.getDirecao().toUpperCase(),
				filtro.getCampo().toUpperCase());
		int proximo = 0;
		if (entidades.hasNext()) {
			Pageable next = entidades.nextPageable();
			proximo = next.getPageNumber();
		}
		int paginaAnterior = 0;
		if (entidades.hasPrevious()) {
			Pageable previous = entidades.previousPageable();
			paginaAnterior = previous.getPageNumber();
		}
		Map<String, Object> parametros = new HashMap<>();
		if (isNotEmpty(filtro.getLogin())) {
			parametros.put("login", filtro.getLogin().toUpperCase());
		}
		UsuarioPaginacaoResource resource = UsuarioPaginacaoResource.builder().lista(resources).build();
		resource.setPage(new PaginacaoRetornoResource(entidades.getNumberOfElements(), entidades.hasContent(),
				entidades.hasNext(), entidades.hasPrevious(), entidades.isFirst(), entidades.isLast(), null, proximo,
				entidades.getNumber(), paginaAnterior, entidades.getSize(), parametros, campoOrdenadoResource,
				entidades.getTotalElements(), entidades.getTotalPages()));
		return resource;
	}

	@Override
	public UsuarioResource buscarPeloId(UsuarioResource resource) {
		Usuario usuario = repository.findById(resource.getId()).orElse(null);
		alterarResource(resource, usuario);
		return resource;
	}

	private void alterarResource(UsuarioResource resource, Usuario usuario) {
		resource.setAvatar(usuario.getAvatar());
		resource.setId(usuario.getId());
		resource.setLogin(usuario.getLogin());
		resource.setSenha(null);
	}
}
