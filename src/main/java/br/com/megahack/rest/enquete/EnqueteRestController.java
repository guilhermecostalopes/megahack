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
import br.com.megahack.core.enqueteresposta.EnqueteRespostaService;
import br.com.megahack.exception.UsuarioException;
import br.com.megahack.rest.MegaHackController;

@RestController
@RequestMapping("/enquetes")
public class EnqueteRestController extends MegaHackController {

	@Autowired
	private EnqueteConsultaService enqueteConsultaService;
	@Autowired
	private EnqueteRespostaService enqueteRespostaService;

	@GetMapping(value = "/votarFavor/{codigoEnquete}/{codigoRepostaEnquete}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> votarFavor(@PathVariable("codPrograma") String codPrograma,
			@PathVariable("dia") Integer dia, @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano,
			@PathVariable("codigoRepostaEnquete") String codigoRepostaEnquete) throws UsuarioException {
		try {
			EnqueteResource resource = enqueteRespostaService.votarFavor(codigoRepostaEnquete, codPrograma, dia, mes,
					ano);
			return new ResponseEntity<>(resource, OK);
		} catch (Exception e) {
			return excecaoGeral(e, "-por-id", "Erro em buscar buscarPorProgramaDia !");
		}
	}

	@GetMapping(value = "/votarContra/{codigoEnquete}/{codigoRepostaEnquete}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> votarContra(@PathVariable("codPrograma") String codPrograma,
			@PathVariable("dia") Integer dia, @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano,
			@PathVariable("codigoRepostaEnquete") String codigoRepostaEnquete) throws UsuarioException {
		try {
			EnqueteResource resource = enqueteRespostaService.votarContra(codigoRepostaEnquete, codPrograma, dia, mes,
					ano);
			return new ResponseEntity<>(resource, OK);
		} catch (Exception e) {
			return excecaoGeral(e, "-por-id", "Erro em buscar buscarPorProgramaDia !");
		}
	}

	@GetMapping(value = "/pesquisarPorPrograma/{codPrograma}/{dia}/{mes}/{ano}", produces = APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> pesquisarPorPrograma(@PathVariable("codPrograma") String codPrograma,
			@PathVariable("dia") Integer dia, @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano)
			throws UsuarioException {
		try {
			EnqueteResource resource = enqueteConsultaService.buscarPorProgramaAndDiaAndMesAndAno(codPrograma, dia, mes,
					ano);
			return new ResponseEntity<>(resource, OK);
		} catch (Exception e) {
			return excecaoGeral(e, "-por-id", "Erro em buscar buscarPorProgramaDia !");
		}
	}
}
