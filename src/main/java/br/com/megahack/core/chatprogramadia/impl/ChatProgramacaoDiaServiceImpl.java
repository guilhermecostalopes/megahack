package br.com.megahack.core.chatprogramadia.impl;

import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.chatprogramadia.ChatProgramacaoDia;
import br.com.megahack.core.chatprogramadia.ChatProgramacaoDiaService;
import br.com.megahack.core.chatprogramadia.resource.ChatProgramacaoDiaResource;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;
import br.com.megahack.core.usuario.Usuario;
import br.com.megahack.core.usuario.UsuarioConsultaService;

@Service
public class ChatProgramacaoDiaServiceImpl implements ChatProgramacaoDiaService {

	@Autowired
	private ChatProgramacaoDiaRepository repository;
	@Autowired
	private UsuarioConsultaService usuarioConsultaService;
	@Autowired
	private ProgramaDiaConsultaService programaDiaConsultaService;

	@Override
	public ChatProgramacaoDiaResource incluir(ChatProgramacaoDiaResource resource) {
		ProgramaDia programaDia = programaDiaConsultaService
				.buscarPorProgramacaoAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFim(resource.getProgramaResource());
		Usuario usuario = usuarioConsultaService.buscarUsuarioPorLogin(resource.getUsuario());
		ChatProgramacaoDia entidade = repository
				.save(ChatProgramacaoDia.builder().programaDia(programaDia).usuario(usuario).build());
		alterarResource(resource, entidade);
		return resource;
	}

	private void alterarResource(ChatProgramacaoDiaResource resource, ChatProgramacaoDia entidade) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(entidade.getProgramaDia().getData());
		Integer ano = calendar.get(GregorianCalendar.YEAR);
		Integer mes = calendar.get(GregorianCalendar.MONTH) + 1;
		Integer dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
		resource.getProgramaResource().setData(StringUtils.leftPad(dia.toString(), 2, "0") + "/"
				+ StringUtils.leftPad(mes.toString(), 2, "0") + "/" + ano.toString());
		calendar = new GregorianCalendar();
		resource.getProgramaResource().setHoraFim(entidade.getProgramaDia().getHoraFim());
		resource.getProgramaResource().setHoraInicio(entidade.getProgramaDia().getHoraInicio());
		resource.getProgramaResource().setDiaSemana(entidade.getProgramaDia().getDiaSemana().getDescricao());
		resource.getProgramaResource().setPrograma(entidade.getProgramaDia().getPrograma().getNome());
		resource.getProgramaResource().setRegiao(entidade.getProgramaDia().getRegiao().getNome());
		resource.setTexto(entidade.getTexto());
		resource.setUsuario(entidade.getUsuario().getPessoa().getNome());
	}
}
