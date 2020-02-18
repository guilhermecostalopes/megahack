package br.com.megahack.rest.usuario;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.megahack.core.usuario.UsuarioConsultaService;
import br.com.megahack.core.usuario.UsuarioService;
import br.com.megahack.core.usuario.resource.UsuarioResource;
import br.com.megahack.exception.UsuarioException;
import br.com.megahack.rest.MegaHackController;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController extends MegaHackController {

	private static final String USUARIO_CHAVE = "usuario-";
	private static final String ERRO_USUARIO_CHAVE = "erro-" + USUARIO_CHAVE;
	private static final String USUARIO = " usuário ";
	@Autowired
	private UsuarioService service;
	@Autowired
	private UsuarioConsultaService consultaService;

	@PostMapping(value = "/incluir", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = CREATED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> incluir(@RequestBody UsuarioResource resource) throws UsuarioException {
		try {
			UsuarioResource incluir = service.incluir(resource);
			return new ResponseEntity<>(incluir, OK);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_USUARIO_CHAVE + "-incluir", "Erro em incluir " + USUARIO + " !");
		}
	}

	@PutMapping(value = "/alterar", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = ACCEPTED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> alterar(@RequestBody UsuarioResource resource) throws UsuarioException {
		try {
			UsuarioResource alterar = service.alterar(resource);
			return new ResponseEntity<>(alterar, OK);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_USUARIO_CHAVE + "-alterar", "Erro em alterar " + USUARIO + " !");
		}
	}

	@DeleteMapping(value = "/excluir/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = ACCEPTED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> exclusao(@PathVariable("id") String id) throws UsuarioException {
		try {
			UsuarioResource excluir = service.excluir(preencherResource(id));
			return new ResponseEntity<>(excluir, OK);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_USUARIO_CHAVE + "-excluir", "Erro em excluir " + USUARIO + " !");
		}
	}

	@GetMapping(value = "/buscar/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = ACCEPTED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> buscarPeloId(@PathVariable("id") String id) throws UsuarioException {
		try {
			UsuarioResource resource = consultaService.buscarPeloId(preencherResource(id));
			return new ResponseEntity<>(resource, OK);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_USUARIO_CHAVE + "-por-id", "Erro em buscar " + USUARIO + " por id !");
		}
	}

	private UsuarioResource preencherResource(String id) {
		UsuarioResource resource = UsuarioResource.builder().build();
		resource.setId(id);
		return resource;
	}
}
