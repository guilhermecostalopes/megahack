package br.com.megahack.core.chatprogramadia.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.chatprogramadia.ChatProgramacaoDia;
import br.com.megahack.core.chatprogramadia.ChatProgramacaoDiaConsultaService;
import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programa.ProgramaConsultaService;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;

@Service
public class ChatProgramacaoDiaConsultaServiceImpl implements ChatProgramacaoDiaConsultaService {

	@Autowired
	private ChatProgramacaoDiaRepository repository;
	@Autowired
	private ProgramaDiaConsultaService programaDiaConsultaService;
	@Autowired
	private ProgramaConsultaService programaConsultaService;

	@Override
	public Collection<ChatProgramacaoDia> buscarPorProgramaAndDiaAndMesAndAno(String codPrograma, Integer dia,
			Integer mes, Integer ano) {
		Programa programa = programaConsultaService.buscarPorCodigo(codPrograma);
		ProgramaDia programaDia = programaDiaConsultaService.buscarPorProgramaDiaMesAno(programa, dia, mes, ano);
		return repository.findByProgramaDia(programaDia);
	}
}
