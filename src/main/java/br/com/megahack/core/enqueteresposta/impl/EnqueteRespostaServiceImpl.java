package br.com.megahack.core.enqueteresposta.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.enquete.Enquete;
import br.com.megahack.core.enquete.EnqueteConsultaService;
import br.com.megahack.core.enquete.resource.EnqueteResource;
import br.com.megahack.core.enqueteresposta.EnqueteResposta;
import br.com.megahack.core.enqueteresposta.EnqueteRespostaService;

@Service
public class EnqueteRespostaServiceImpl implements EnqueteRespostaService {

	@Autowired
	private EnqueteRespostaRepository repository;
	@Autowired
	private EnqueteConsultaService enqueteConsultaService;

	@Override
	public EnqueteResource votarFavor(String codigoRepostaEnquete, String codigoEnquete, String codPrograma,
			Integer dia, Integer mes, Integer ano) {
		Enquete enquete = enqueteConsultaService.buscaPorCodigo(codigoEnquete);
		EnqueteResposta enqueteResposta = buscarEnqueteRespostaPorCodigoEnquete(codigoRepostaEnquete, enquete);
		Integer votacaoFavor = enqueteResposta.getVotacaoFavor();
		enqueteResposta.setVotacaoFavor(votacaoFavor + 1);
		repository.save(enqueteResposta);
		return enqueteConsultaService.buscarPorProgramaAndDiaAndMesAndAno(codPrograma, dia, mes, ano);
	}

	@Override
	public EnqueteResource votarContra(String codigoRepostaEnquete, String codigoEnquete, String codPrograma,
			Integer dia, Integer mes, Integer ano) {
		Enquete enquete = enqueteConsultaService.buscaPorCodigo(codigoEnquete);
		EnqueteResposta enqueteResposta = buscarEnqueteRespostaPorCodigoEnquete(codigoRepostaEnquete, enquete);
		Integer votacaoContra = enqueteResposta.getVotacaoContra();
		enqueteResposta.setVotacaoContra(votacaoContra + 1);
		repository.save(enqueteResposta);
		return enqueteConsultaService.buscarPorProgramaAndDiaAndMesAndAno(codPrograma, dia, mes, ano);
	}

	private EnqueteResposta buscarEnqueteRespostaPorCodigoEnquete(String codigoRepostaEnquete, Enquete enquete) {
		return repository.findByCodigoAndEnquete(codigoRepostaEnquete, enquete);
	}
}
