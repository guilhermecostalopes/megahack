package br.com.megahack.rest.chatprogramacaodia;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.megahack.core.chatprogramadia.ChatProgramacaoDia;
import br.com.megahack.core.chatprogramadia.ChatProgramacaoDiaConsultaService;
import br.com.megahack.core.chatprogramadia.ChatProgramacaoDiaService;
import br.com.megahack.core.chatprogramadia.resource.ChatProgramacaoDiaResource;
import br.com.megahack.core.programadia.resource.ProgramaDiaResource;
import br.com.megahack.rest.MegaHackController;

@RestController
@RequestMapping("/chatProgramacaoDias")
public class ChatProgramacaoDiaRestController extends MegaHackController {

	private static final String CHAT_PROGRAMACAO_DIA_CHAVE = "chatprogramacaodia-";
	private static final String ERRO_CHAT_PROGRAMACAO_DIA_CHAVE = "erro-" + CHAT_PROGRAMACAO_DIA_CHAVE;
	private static final String CHAT_PROGRAMACAO_DIA = " chat da programção diária ";
	@Autowired
	private ChatProgramacaoDiaService chatProgramacaoDiaService;
	@Autowired
	private ChatProgramacaoDiaConsultaService chatProgramacaoDiaConsultaService;

	@PostMapping(value = "/incluir", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = CREATED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR)")
	public ResponseEntity<?> incluir(@RequestBody ChatProgramacaoDiaResource resource) {
		try {
			ChatProgramacaoDiaResource incluir = chatProgramacaoDiaService.incluir(resource);
			return new ResponseEntity<>(incluir, OK);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_CHAT_PROGRAMACAO_DIA_CHAVE + "-incluir",
					"Erro em incluir " + CHAT_PROGRAMACAO_DIA + "!");
		}
	}

	@PutMapping(value = "/pesquisarPorPrograma", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(code = ACCEPTED)
	// @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
	public ResponseEntity<?> buscarPaginacao(ProgramaDiaResource resource) {
		try {
			Collection<ChatProgramacaoDia> pesquisa = chatProgramacaoDiaConsultaService
					.buscarPorProgramacaoDia(resource);
			return new ResponseEntity<>(pesquisa, OK);
		} catch (Exception e) {
			return excecaoGeral(e, ERRO_CHAT_PROGRAMACAO_DIA_CHAVE + "-pesquisar",
					"Erro em pesquisar " + CHAT_PROGRAMACAO_DIA + " !");
		}
	}
}
