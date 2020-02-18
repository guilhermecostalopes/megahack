package br.com.megahack.core.programadia.impl;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.megahack.core.enuns.DiaSemanaEnum;
import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.programa.ProgramaConsultaService;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaService;
import br.com.megahack.core.programadia.resource.ProgramaDiaResource;
import br.com.megahack.core.regiao.Regiao;
import br.com.megahack.core.regiao.RegiaoConsultaService;

@Service
public class ProgramaDiaServiceImpl implements ProgramaDiaService {

	@Autowired
	private ProgramaDiaRepository repository;
	@Autowired
	private ProgramaConsultaService programaConsultaService;
	@Autowired
	private RegiaoConsultaService regiaoConsultaService;

	@Override
	public ProgramaDiaResource incluir(ProgramaDiaResource resource) {
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
		calendar = new GregorianCalendar();
		calendar.set(Integer.parseInt(dtSplit[2]), Integer.parseInt(dtSplit[1]), Integer.parseInt(dtSplit[0]));
		Date data = calendar.getTime();
		calendar.set(Integer.parseInt(dtSplit[2]), Integer.parseInt(dtSplit[1]), Integer.parseInt(dtSplit[0]),
				Integer.parseInt(hiSplit[0]), Integer.parseInt(hiSplit[1]), Integer.parseInt(hiSplit[2]));
		Date horaInicio = calendar.getTime();
		calendar = new GregorianCalendar();
		calendar.set(Integer.parseInt(dtSplit[2]), Integer.parseInt(dtSplit[1]), Integer.parseInt(dtSplit[0]),
				Integer.parseInt(hfSplit[0]), Integer.parseInt(hfSplit[1]), Integer.parseInt(hfSplit[2]));
		Date horaFim = calendar.getTime();
		ProgramaDia entidade = repository.save(ProgramaDia.builder().programa(programa).data(data).diaSemana(diaSemana)
				.horaInicio(horaInicio).horaFim(horaFim).regiao(regiao).build());
		alterarResource(resource, entidade);
		return resource;
	}

	private void alterarResource(ProgramaDiaResource resource, ProgramaDia entidade) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(entidade.getData());
		Integer ano = calendar.get(GregorianCalendar.YEAR);
		Integer mes = calendar.get(GregorianCalendar.MONTH) + 1;
		Integer dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
		resource.setData(StringUtils.leftPad(dia.toString(), 2, "0") + "/" + StringUtils.leftPad(mes.toString(), 2, "0")
				+ "/" + ano.toString());
		calendar = new GregorianCalendar();
		calendar.setTime(entidade.getHoraInicio());
		Integer hora = calendar.get(GregorianCalendar.HOUR);
		Integer minuto = calendar.get(GregorianCalendar.MINUTE);
		Integer segundo = calendar.get(GregorianCalendar.SECOND);
		resource.setHoraFim(
				StringUtils.leftPad(hora.toString(), 2, "0") + "/" + StringUtils.leftPad(minuto.toString(), 2, "0")
						+ "/" + StringUtils.leftPad(segundo.toString(), 2, "0"));
		calendar = new GregorianCalendar();
		calendar.setTime(entidade.getHoraFim());
		hora = calendar.get(GregorianCalendar.HOUR);
		minuto = calendar.get(GregorianCalendar.MINUTE);
		segundo = calendar.get(GregorianCalendar.SECOND);
		resource.setHoraInicio(
				StringUtils.leftPad(hora.toString(), 2, "0") + "/" + StringUtils.leftPad(minuto.toString(), 2, "0")
						+ "/" + StringUtils.leftPad(segundo.toString(), 2, "0"));
		resource.setDiaSemana(entidade.getDiaSemana().getDescricao());
		resource.setPrograma(entidade.getPrograma().getNome());
		resource.setRegiao(entidade.getRegiao().getNome());
	}
}
