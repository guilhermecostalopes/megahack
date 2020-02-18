package br.com.megahack.core.chatprogramadia.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.chatprogramadia.ChatProgramacaoDia;
import br.com.megahack.core.chatprogramadia.ChatProgramacaoDiaConsultaService;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;

@Service
public class ChatProgramacaoDiaConsultaServiceImpl implements ChatProgramacaoDiaConsultaService {

	@Autowired
	private ChatProgramacaoDiaRepository repository;
	@Autowired
	private ProgramaDiaConsultaService programaDiaConsultaService;

	@Override
	public Collection<ChatProgramacaoDia> buscarPorProgramacaoDia(String idPrograma) {
		ProgramaDia programaDia = programaDiaConsultaService.buscarPorId(idPrograma);
		return repository.findByProgramaDia(programaDia);
	}
}
