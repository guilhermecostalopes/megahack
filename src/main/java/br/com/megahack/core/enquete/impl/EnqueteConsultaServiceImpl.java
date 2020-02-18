package br.com.megahack.core.enquete.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.enquete.Enquete;
import br.com.megahack.core.enquete.EnqueteConsultaService;
import br.com.megahack.core.enquete.resource.EnqueteResource;
import br.com.megahack.core.enqueteresposta.EnqueteResposta;
import br.com.megahack.core.enqueteresposta.resource.EnqueteRespostaResource;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;
import br.com.megahack.core.programadia.resource.ProgramaDiaResource;

@Service
public class EnqueteConsultaServiceImpl implements EnqueteConsultaService {

	@Autowired
	private EnqueteRepository repository;
	@Autowired
	private ProgramaDiaConsultaService programaDiaConsultaService;

	@Override
	public EnqueteResource buscarPorProgramaDia(EnqueteResource resource) {
		ProgramaDia programaDia = programaDiaConsultaService
				.buscarPorProgramacaoAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFim(resource.getProgramaDiaResource());
		Enquete entidade = repository.findByProgramaDia(programaDia);
		alterarResource(resource, entidade);
		return resource;
	}

	private void alterarResource(EnqueteResource resource, Enquete entidade) {
		resource.setDataHoraFim(null);
		resource.setDataHoraInicio(null);
		resource.setPergunta(entidade.getPergunta());
		Collection<EnqueteRespostaResource> resources = new ArrayList<>();
		incluirRespostaResource(resources, entidade.getEnqueteResposta());
		resource.setRespostas(resources);
		resource.setProgramaDiaResource(ProgramaDiaResource.builder().data(null).diaSemana(null).horaFim(null)
				.horaInicio(null).programa(entidade.getProgramaDia().getPrograma().getNome())
				.regiao(entidade.getProgramaDia().getRegiao().getNome()).build());
	}

	private void incluirRespostaResource(Collection<EnqueteRespostaResource> resources,
			Collection<EnqueteResposta> entidades) {
		entidades.forEach(e -> resources.add(EnqueteRespostaResource.builder().resposta(e.getResposta())
				.votacaoContra(e.getVotacaoContra()).votacaoFavor(e.getVotacaoFavor()).build()));
	}
}
