package br.com.megahack.core.programadia.impl;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.enuns.DiaSemanaEnum;
import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programa.ProgramaConsultaService;
import br.com.megahack.core.programa.resource.ProgramaResource;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;
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
	public ProgramaDia buscarPorProgramacaoAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFim(ProgramaResource resource) {
		Programa programa = programaConsultaService.buscarPorNome(resource.getPrograma());
		Regiao regiao = regiaoConsultaService.buscarPorNome(resource.getRegiao());
		DiaSemanaEnum diaSemana = DiaSemanaEnum.buscaTipoEnum(resource.getDiaSemana());
		String dt = resource.getData();
		String hi = resource.getHoraFim();
		String hf = resource.getHoraInicio();
		String[] hiSplit = hi.split(":");
		String[] hfSplit = hf.split(":");
		String[] dtSplit = dt.split("/");
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Integer.parseInt(dtSplit[2]), Integer.parseInt(dtSplit[1]), Integer.parseInt(dtSplit[0]),
				Integer.parseInt(hiSplit[0]), Integer.parseInt(hiSplit[1]), Integer.parseInt(hiSplit[2]));
		Date horaInicio = calendar.getTime();
		calendar = new GregorianCalendar();
		calendar.set(Integer.parseInt(dtSplit[2]), Integer.parseInt(dtSplit[1]), Integer.parseInt(dtSplit[0]),
				Integer.parseInt(hfSplit[0]), Integer.parseInt(hfSplit[4]), Integer.parseInt(hfSplit[2]));
		Date horaFim = calendar.getTime();
		return repository.findByProgramacaoAndRegiaoAndDiaSemanaAndHoraInicioAndHoraFim(programa, regiao, diaSemana,
				horaInicio, horaFim);
	}
}
