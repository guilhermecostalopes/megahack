package br.com.megahack.core.enquete.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.enquete.Enquete;
import br.com.megahack.core.enquete.EnqueteConsultaService;
import br.com.megahack.core.enquete.resource.EnqueteResource;
import br.com.megahack.core.enqueteresposta.EnqueteResposta;
import br.com.megahack.core.enqueteresposta.resource.EnqueteRespostaResource;
import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programa.ProgramaConsultaService;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;
import br.com.megahack.core.programadia.resource.ProgramaDiaResource;

@Service
public class EnqueteConsultaServiceImpl implements EnqueteConsultaService {

	@Autowired
	private EnqueteRepository repository;
	@Autowired
	private ProgramaDiaConsultaService programaDiaConsultaService;
	@Autowired
	private ProgramaConsultaService programaConsultaService;

	@Override
	public EnqueteResource buscarPorProgramaAndDiaAndMesAndAno(String codPrograma, Integer dia, Integer mes,
			Integer ano) {
		Programa programa = programaConsultaService.buscarPorCodigo(codPrograma);
		ProgramaDia programaDia = programaDiaConsultaService.buscarPorProgramaDiaMesAno(programa, dia, mes, ano);
		Enquete entidade = repository.findByProgramaDia(programaDia);
		return alterarResource(entidade);
	}

	@Override
	public Enquete buscaPorCodigo(String codigo) {
		return repository.findByCodigo(codigo);
	}

	private EnqueteResource alterarResource(Enquete entidade) {
		Collection<EnqueteRespostaResource> resources = new ArrayList<>();
		Integer totalVotos = 0;
		incluirRespostaResource(resources, entidade.getEnqueteResposta(), totalVotos);

		GregorianCalendar calendario = new GregorianCalendar();
		calendario.setTime(entidade.getDataHoraFim());

		Integer diaFim = calendario.get(Calendar.DAY_OF_WEEK);
		Integer mesFim = calendario.get(Calendar.MONTH) + 1;
		Integer anoFim = calendario.get(Calendar.YEAR);
		Integer horaFim = calendario.get(Calendar.HOUR);
		Integer minutoFim = calendario.get(Calendar.MINUTE);
		Integer segundoFim = calendario.get(Calendar.SECOND);

		calendario = new GregorianCalendar();
		calendario.setTime(entidade.getDataHoraInicio());

		Integer diaIni = calendario.get(Calendar.DAY_OF_WEEK);
		Integer mesIni = calendario.get(Calendar.MONTH) + 1;
		Integer anoIni = calendario.get(Calendar.YEAR);
		Integer horaIni = calendario.get(Calendar.HOUR);
		Integer minutoIni = calendario.get(Calendar.MINUTE);
		Integer segundoIni = calendario.get(Calendar.SECOND);

		calendario = new GregorianCalendar();
		Integer dia = calendario.get(Calendar.DAY_OF_WEEK);
		Integer mes = calendario.get(Calendar.MONTH) + 1;
		Integer ano = calendario.get(Calendar.YEAR);

		EnqueteResource resource = EnqueteResource.builder().codigo(entidade.getCodigo())
				.codPrograma(entidade.getProgramaDia().getPrograma().getCodigo()).pergunta(entidade.getPergunta())
				.dataHoraFim(StringUtils.leftPad(diaFim.toString(), 2, "0") + "/"
						+ StringUtils.leftPad(mesFim.toString(), 2, "0") + "/"
						+ StringUtils.leftPad(anoFim.toString(), 2, "0") + " às "
						+ StringUtils.leftPad(horaFim.toString(), 2, "0") + ":"
						+ StringUtils.leftPad(minutoFim.toString(), 2, "0") + ":"
						+ StringUtils.leftPad(segundoFim.toString(), 2, "0"))
				.dataHoraInicio(StringUtils.leftPad(diaIni.toString(), 2, "0") + "/"
						+ StringUtils.leftPad(mesIni.toString(), 2, "0") + "/"
						+ StringUtils.leftPad(anoIni.toString(), 2, "0") + " às "
						+ StringUtils.leftPad(horaIni.toString(), 2, "0") + ":"
						+ StringUtils.leftPad(minutoIni.toString(), 2, "0") + ":"
						+ StringUtils.leftPad(segundoIni.toString(), 2, "0"))
				.respostas(resources)
				.programaDiaResource(ProgramaDiaResource.builder()
						.data(StringUtils.leftPad(dia.toString(), 2, "0") + "/"
								+ StringUtils.leftPad(mes.toString(), 2, "0") + "/"
								+ StringUtils.leftPad(ano.toString(), 2, "0"))
						.diaSemana(entidade.getProgramaDia().getDiaSemana().getDescricao())
						.horaFim(entidade.getProgramaDia().getHoraFim())
						.horaInicio(entidade.getProgramaDia().getHoraInicio())
						.programa(entidade.getProgramaDia().getPrograma().getNome())
						.regiao(entidade.getProgramaDia().getRegiao().getNome()).build())
				.build();
		return resource;
	}

	private void incluirRespostaResource(Collection<EnqueteRespostaResource> resources,
			Collection<EnqueteResposta> entidades, Integer totalVotos) {
		for (EnqueteResposta e : entidades) {
			totalVotos = e.getVotacaoContra() + e.getVotacaoFavor();
			resources.add(EnqueteRespostaResource.builder().resposta(e.getResposta()).codigo(e.getCodigo())
					.votacaoContra(e.getVotacaoContra()).votacaoFavor(e.getVotacaoFavor()).build());
		}
	}
}
