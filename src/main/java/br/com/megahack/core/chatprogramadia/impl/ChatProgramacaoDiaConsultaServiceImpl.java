package br.com.megahack.core.chatprogramadia.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.chatprogramadia.ChatProgramacaoDia;
import br.com.megahack.core.chatprogramadia.ChatProgramacaoDiaConsultaService;
import br.com.megahack.core.chatprogramadia.resource.ChatProgramacaoDiaResource;
import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programa.ProgramaConsultaService;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;
import br.com.megahack.core.programadia.resource.ProgramaDiaResource;

@Service
public class ChatProgramacaoDiaConsultaServiceImpl implements ChatProgramacaoDiaConsultaService {

	@Autowired
	private ChatProgramacaoDiaRepository repository;
	@Autowired
	private ProgramaDiaConsultaService programaDiaConsultaService;
	@Autowired
	private ProgramaConsultaService programaConsultaService;

	@Override
	public Collection<ChatProgramacaoDiaResource> buscarPorProgramaAndDiaAndMesAndAno(String codPrograma, Integer dia,
			Integer mes, Integer ano) {
		Programa programa = programaConsultaService.buscarPorCodigo(codPrograma);
		ProgramaDia programaDia = programaDiaConsultaService.buscarPorProgramaDiaMesAno(programa, dia, mes, ano);
		Collection<ChatProgramacaoDia> chats = repository.findByProgramaDia(programaDia);
		Collection<ChatProgramacaoDiaResource> resources = new ArrayList<>();
		alterarResource(resources, chats);
		return resources;
	}

	private void alterarResource(Collection<ChatProgramacaoDiaResource> resources,
			Collection<ChatProgramacaoDia> chats) {
		chats.forEach(c -> {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(c.getProgramaDia().getData());
			Integer ano = calendar.get(GregorianCalendar.YEAR);
			Integer mes = calendar.get(GregorianCalendar.MONTH) + 1;
			Integer dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
			resources.add(ChatProgramacaoDiaResource.builder().codPrograma(c.getProgramaDia().getPrograma().getCodigo())
					.texto(c.getTexto())
					.usuario(c.getUsuario().getPessoa().getNome())
					.programaResource(ProgramaDiaResource.builder()
							.data(StringUtils.leftPad(dia.toString(), 2, "0") + "/"
									+ StringUtils.leftPad(mes.toString(), 2, "0") + "/" + ano.toString())
							.diaSemana(c.getProgramaDia().getDiaSemana().getDescricao())
							.horaFim(c.getProgramaDia().getHoraFim()).horaInicio(c.getProgramaDia().getHoraInicio())
							.programa(c.getProgramaDia().getPrograma().getNome())
							.regiao(c.getProgramaDia().getRegiao().getNome()).build())
					.build());
		});
	}
}
