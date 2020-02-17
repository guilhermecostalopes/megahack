package br.com.megahack.core.chatprogramadia.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.chatprogramadia.ChatProgramacaoDia;
import br.com.megahack.core.chatprogramadia.ChatProgramacaoDiaService;
import br.com.megahack.core.chatprogramadia.resource.ChatProgramacaoDiaResource;
import br.com.megahack.core.usuario.Usuario;
import br.com.megahack.core.usuario.UsuarioConsultaService;

@Service
public class ChatProgramacaoDiaImpl implements ChatProgramacaoDiaService {

	@Autowired
	private ChatProgramacaoDiaRepository repository;
	@Autowired
	private UsuarioConsultaService usuarioConsultaService;

	@Override
	public ChatProgramacaoDiaResource incluir(ChatProgramacaoDiaResource resource) {
		Usuario usuario = usuarioConsultaService.buscarUsuarioPorLogin(resource.getUsuario());
		repository.save(ChatProgramacaoDia.builder()
				.programaDia(null)
				.usuario(usuario)
				.build());
		return null;
	}
}
