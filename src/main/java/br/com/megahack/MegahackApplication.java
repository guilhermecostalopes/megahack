package br.com.megahack;

import static br.com.megahack.Converter.imageToByte;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.megahack.core.cidade.Cidade;
import br.com.megahack.core.cidade.CidadeConsultaService;
import br.com.megahack.core.cidade.CidadeService;
import br.com.megahack.core.cidade.resource.CidadeResource;
import br.com.megahack.core.estado.Estado;
import br.com.megahack.core.estado.EstadoConsultaService;
import br.com.megahack.core.estado.EstadoService;
import br.com.megahack.core.estado.resource.EstadoResource;
import br.com.megahack.core.grupo.GrupoService;
import br.com.megahack.core.grupo.resource.GrupoResource;
import br.com.megahack.core.regiao.Regiao;
import br.com.megahack.core.regiao.RegiaoConsultaService;
import br.com.megahack.core.regiao.RegiaoService;
import br.com.megahack.core.regiao.resource.RegiaoResource;
import br.com.megahack.core.usuario.UsuarioConsultaService;
import br.com.megahack.core.usuario.UsuarioService;
import br.com.megahack.core.usuario.resource.UsuarioResource;
import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@CommonsLog
public class MegahackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegahackApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioService usuarioService, UsuarioConsultaService usuarioConsultaService,
			RegiaoService regiaoService, EstadoService service, CidadeService cidadeService,
			RegiaoConsultaService regiaoConsultaService, EstadoConsultaService estadoConsultaService,
			CidadeConsultaService cidadeConsultaService, GrupoService grupoService) {
		return args -> {
			incluirGrupo(grupoService);
			incluirRegiao(regiaoService);
			Regiao regiao = regiaoConsultaService.buscarPorNome("Sudeste");
			incluirEstado(regiao, service);
			Estado estado = estadoConsultaService.buscarPorNome("Minas Gerais");
			incluirCidade(estado, cidadeService);
			Cidade cidade = cidadeConsultaService.buscarPorNome("Belo Horizonte");
			incluirUsuariosPessoas(usuarioService, usuarioConsultaService, cidade);
		};
	}

	private void incluirUsuariosPessoas(UsuarioService usuarioService, UsuarioConsultaService usuarioConsultaService,
			Cidade cidade) {
		try {
			byte[] avatarMasculino = imageToByte("/avatar/avatar_masculino.jfif");
			usuarioService.incluir(UsuarioResource.builder().login("85655497042").senha("y|e8HTcAJ9").nome("Guilherme")
					.sobreNome("Costa Lopes").dataAniversario("06/07/1978").cidade("Belo Horizonte")
					.avatar(avatarMasculino).grupo("Administrador").build());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void incluirRegiao(RegiaoService regiaoService) {
		regiaoService.incluir(RegiaoResource.builder().nome("Sudeste").build());
	}

	private void incluirEstado(Regiao regiao, EstadoService service) {
		service.incluir(EstadoResource.builder().nome("Minas Gerais").regiao("Sudeste").abreviacao("MG").build());
	}

	private void incluirCidade(Estado estado, CidadeService cidadeService) {
		cidadeService.incluir(CidadeResource.builder().nome("Belo Horizonte").estado("Minas Gerais").build());
	}

	private void incluirGrupo(GrupoService grupoService) {
		grupoService.incluir(GrupoResource.builder().nome("Administrador").role("ROLE_ADMINISTRADOR").build());
	}
}
