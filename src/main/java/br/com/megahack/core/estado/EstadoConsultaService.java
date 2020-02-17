package br.com.megahack.core.estado;

public interface EstadoConsultaService {

	Estado buscarPorNome(String nome);
	
	Estado buscarPorAbreviacao(String abreviacao);
}
