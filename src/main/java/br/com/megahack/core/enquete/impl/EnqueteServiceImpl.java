package br.com.megahack.core.enquete.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.enquete.Enquete;
import br.com.megahack.core.enquete.EnqueteService;
import br.com.megahack.core.enquete.resource.EnqueteResource;
import br.com.megahack.core.enqueteresposta.EnqueteResposta;
import br.com.megahack.core.enqueteresposta.resource.EnqueteRespostaResource;
import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programa.ProgramaConsultaService;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;
import br.com.megahack.core.programadia.resource.ProgramaDiaResource;

@Service
public class EnqueteServiceImpl implements EnqueteService {

	@Autowired
	private EnqueteRepository repository;
	@Autowired
	private ProgramaDiaConsultaService programaDiaConsultaService;
	@Autowired
	private ProgramaConsultaService programaConsultaService;

	@Override
	public EnqueteResource incluir(EnqueteResource resource) {
		Programa programa = programaConsultaService.buscarPorCodigo(resource.getCodPrograma());
		String[] dtSplit = resource.getProgramaDiaResource().getData().split("/");
		ProgramaDia programaDia = programaDiaConsultaService.buscarPorProgramaDiaMesAno(programa,
				Integer.parseInt(dtSplit[0]), Integer.parseInt(dtSplit[1]), Integer.parseInt(dtSplit[2]));
		String dtHrFim = resource.getDataHoraFim();
		String dtHrIni = resource.getDataHoraInicio();
		String[] dtHrFimSplit = dtHrFim.split("_");
		String[] dtFimSplit = dtHrFimSplit[0].split("/");
		String[] hrFimSplit = dtHrFimSplit[1].split(":");
		String[] dtHrIniSplit = dtHrIni.split("_");
		String[] dtIniSplit = dtHrIniSplit[0].split("/");
		String[] hrIniSplit = dtHrIniSplit[1].split(":");
		GregorianCalendar calendarFim = new GregorianCalendar();
		calendarFim.set(Integer.parseInt(dtFimSplit[2]), Integer.parseInt(dtFimSplit[1]),
				Integer.parseInt(dtFimSplit[0]), Integer.parseInt(hrFimSplit[0]), Integer.parseInt(hrFimSplit[1]),
				Integer.parseInt(hrFimSplit[2]));
		GregorianCalendar calendarInicio = new GregorianCalendar();
		calendarInicio.set(Integer.parseInt(dtIniSplit[2]), Integer.parseInt(dtIniSplit[1]),
				Integer.parseInt(dtIniSplit[0]), Integer.parseInt(hrIniSplit[0]), Integer.parseInt(hrIniSplit[1]),
				Integer.parseInt(hrIniSplit[2]));
		Enquete entidade = Enquete.builder().codigo(resource.getCodigo()).dataHoraFim(calendarFim.getTime())
				.dataHoraInicio(calendarInicio.getTime()).pergunta(resource.getPergunta()).programaDia(programaDia)
				.build();
		Collection<EnqueteResposta> enqueteResposta = new ArrayList<>();
		incluirRespostaEntidade(resource.getRespostas(), enqueteResposta, entidade);
		entidade.setEnqueteResposta(enqueteResposta);
		entidade = repository.save(entidade);
		alterarResource(resource, entidade);
		return resource;
	}

	private void alterarResource(EnqueteResource resource, Enquete entidade) {
		resource.setDataHoraFim(resource.getDataHoraFim());
		resource.setDataHoraInicio(resource.getDataHoraInicio());
		resource.setPergunta(entidade.getPergunta());
		Collection<EnqueteRespostaResource> resources = new ArrayList<>();
		incluirRespostaResource(resources, entidade.getEnqueteResposta());
		resource.setRespostas(resources);
		resource.setProgramaDiaResource(ProgramaDiaResource.builder().data(null).diaSemana(null).horaFim(null)
				.horaInicio(null).programa(entidade.getProgramaDia().getPrograma().getNome())
				.regiao(entidade.getProgramaDia().getRegiao().getNome()).build());
	}

	private void incluirRespostaEntidade(Collection<EnqueteRespostaResource> resources,
			Collection<EnqueteResposta> entidades, Enquete entidade) {
		resources.forEach(r -> entidades.add(EnqueteResposta.builder().codigo(r.getCodigo()).resposta(r.getResposta())
				.votacaoContra(0).votacaoFavor(0).enquete(entidade).build()));
	}

	private void incluirRespostaResource(Collection<EnqueteRespostaResource> resources,
			Collection<EnqueteResposta> entidades) {
		entidades.forEach(e -> resources.add(EnqueteRespostaResource.builder().resposta(e.getResposta())
				.votacaoContra(e.getVotacaoContra()).votacaoFavor(e.getVotacaoFavor()).build()));
	}
}
