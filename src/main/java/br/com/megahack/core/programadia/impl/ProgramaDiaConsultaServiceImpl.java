package br.com.megahack.core.programadia.impl;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.enuns.DiaSemanaEnum;
import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programa.ProgramaConsultaService;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;
import br.com.megahack.core.programadia.resource.ProgramaDiaResource;
import br.com.megahack.core.regiao.Regiao;
import br.com.megahack.core.regiao.RegiaoConsultaService;

@Service
public class ProgramaDiaConsultaServiceImpl implements ProgramaDiaConsultaService {

	@Autowired
	private ProgramaDiaRepository repository;
	@Autowired
	private ProgramaConsultaService programaConsultaService;
	@Autowired
	private RegiaoConsultaService regiaoConsultaService;

	@Override
	public ProgramaDia buscarPorProgramacaoAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFim(ProgramaDiaResource resource) {
		Programa programa = programaConsultaService.buscarPorNome(resource.getPrograma());
		Regiao regiao = regiaoConsultaService.buscarPorNome(resource.getRegiao());
		DiaSemanaEnum diaSemana = DiaSemanaEnum.buscaTipoEnum(resource.getDiaSemana());
		String dt = resource.getData();
		String[] dtSplit = dt.split("/");
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Integer.parseInt(dtSplit[2]), Integer.parseInt(dtSplit[1]), Integer.parseInt(dtSplit[0]));
		Date data = calendar.getTime();
		return repository.findByProgramaAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFimAndData(programa, regiao, diaSemana,
				resource.getHoraInicio(), resource.getHoraFim(), data);
	}
}
