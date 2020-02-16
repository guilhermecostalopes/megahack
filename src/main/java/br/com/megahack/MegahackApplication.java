package br.com.megahack;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.megahack.core.cidade.Cidade;
import br.com.megahack.core.cidade.CidadeService;
import br.com.megahack.core.cidade.resource.CidadeResource;
import br.com.megahack.core.estado.Estado;
import br.com.megahack.core.estado.EstadoService;
import br.com.megahack.core.estado.resource.EstadoResource;
import br.com.megahack.core.regiao.Regiao;
import br.com.megahack.core.regiao.RegiaoService;
import br.com.megahack.core.regiao.resource.RegiaoResource;
import br.com.megahack.core.usuario.UsuarioConsultaService;
import br.com.megahack.core.usuario.UsuarioService;
import br.com.megahack.core.usuario.resource.UsuarioResource;

@SpringBootApplication
public class MegahackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegahackApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioService usuarioService, UsuarioConsultaService usuarioConsultaService, RegiaoService regiaoService,
			EstadoService service, CidadeService cidadeService) {
		return args -> {
		};
	}

	private void incluirUsuariosPessoas(UsuarioService usuarioService, UsuarioConsultaService usuarioConsultaService, Cidade cidade) {

		usuarioService.incluir(UsuarioResource.builder()
				.login("85655497042")
				.senha("y|e8HTcAJ9")
				.nome("Guilherme")
				.sobreNome("Costa Lopes")
				.dataAniversario("06/07/1978")
				.cidade("Belo Horizonte")
				.build());
	}
	
	private void incluirRegiao(RegiaoService regiaoService) {
		regiaoService.incluir(RegiaoResource.builder()
				.nome("Sudeste")
				.build());
	}
	
	private void incluirEstado(Regiao regiao, EstadoService service) {
		service.incluir(EstadoResource.builder()
				.nome("Minas Gerais")
				.regiao("Sudeste")
				.build()); 
	}
	
	private void incluirCidade(Estado estado, CidadeService cidadeService) {
		cidadeService.incluir(CidadeResource.builder()
				.nome("Belo Horizonte")
				.estado("Minas Gerais")
				.build());
	}
}
