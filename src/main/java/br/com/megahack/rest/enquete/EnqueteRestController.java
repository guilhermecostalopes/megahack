package br.com.megahack.rest.enquete;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.megahack.core.enquete.EnqueteConsultaService;
import br.com.megahack.core.enquete.resource.EnqueteResource;
import br.com.megahack.exception.UsuarioException;
import br.com.megahack.rest.MegaHackController;

@RestController
@RequestMapping("/enquetes")
public class EnqueteRestController extends MegaHackController {

	@Autowired
	private EnqueteConsultaService enqueteConsultaService;

	@GetMapping(value = "/buscarPorProgramaDia/{idPrograma}", produces = APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> buscarPorProgramaDia(@PathVariable("idPrograma") String idPrograma)
			throws UsuarioException {
		try {
			EnqueteResource resource = enqueteConsultaService.buscarPorProgramaDia(idPrograma);
			return new ResponseEntity<>(resource, OK);
		} catch (Exception e) {
			return excecaoGeral(e, "-por-id", "Erro em buscar buscarPorProgramaDia !");
		}
	}
}
