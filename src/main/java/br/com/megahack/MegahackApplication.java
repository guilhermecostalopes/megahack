package br.com.megahack;

import static br.com.megahack.Converter.imageToByte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.megahack.core.chatprogramadia.ChatProgramacaoDiaService;
import br.com.megahack.core.chatprogramadia.resource.ChatProgramacaoDiaResource;
import br.com.megahack.core.cidade.CidadeService;
import br.com.megahack.core.cidade.resource.CidadeResource;
import br.com.megahack.core.enquete.EnqueteService;
import br.com.megahack.core.enquete.resource.EnqueteResource;
import br.com.megahack.core.enqueteresposta.resource.EnqueteRespostaResource;
import br.com.megahack.core.estado.EstadoService;
import br.com.megahack.core.estado.resource.EstadoResource;
import br.com.megahack.core.grupo.GrupoService;
import br.com.megahack.core.grupo.resource.GrupoResource;
import br.com.megahack.core.programa.ProgramaService;
import br.com.megahack.core.programa.resource.ProgramaResource;
import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.programadia.ProgramaDiaConsultaService;
import br.com.megahack.core.programadia.ProgramaDiaService;
import br.com.megahack.core.programadia.resource.ProgramaDiaResource;
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
			RegiaoService regiaoService, EstadoService service, CidadeService cidadeService, GrupoService grupoService,
			ProgramaService programaService, ProgramaDiaService programaDiaService, EnqueteService enqueteService,
			ProgramaDiaConsultaService programaDiaConsultaService,
			ChatProgramacaoDiaService chatProgramacaoDiaService) {
		return args -> {
			incluirGrupo(grupoService);
			incluirRegiao(regiaoService);
			incluirEstado(service);
			incluirCidade(cidadeService);
			incluirUsuariosPessoas(usuarioService, usuarioConsultaService);
			incluirPrograma(programaService);
			incluirProgramaDia(programaDiaService);
			Collection<ProgramaDia> programaDias = programaDiaConsultaService.buscarTodos();
			ProgramaDia programaDia = programaDias.stream().filter(f -> f.getPrograma().getCodigo().equals("001"))
					.findAny().orElse(null);
			incluirEnquete(enqueteService, programaDia.getPrograma().getCodigo());
			incluirChat(chatProgramacaoDiaService);
		};
	}

	private void incluirChat(ChatProgramacaoDiaService chatProgramacaoDiaService) {
		chatProgramacaoDiaService.incluir(ChatProgramacaoDiaResource.builder()
				.codPrograma("001")
				.texto("Tudo bem Fátima Bernades ?")
				.usuario("85655497042")
				.programaResource(programaDiaResource())
				.build());
		chatProgramacaoDiaService.incluir(ChatProgramacaoDiaResource.builder()
				.codPrograma("001")
				.texto("O programa de hoje está ótimo ... Parabéns ...")
				.usuario("85655497041")
				.programaResource(programaDiaResource())
				.build());
		chatProgramacaoDiaService.incluir(ChatProgramacaoDiaResource.builder()
				.codPrograma("001")
				.texto("Esepero que amanhã continue os mesmos artistas ...")
				.usuario("85655497043")
				.programaResource(programaDiaResource())
				.build());
		chatProgramacaoDiaService.incluir(ChatProgramacaoDiaResource.builder()
				.codPrograma("001")
				.texto("Como sabe fazer um ótimo programa, essa Fátima Bernades")
				.usuario("85655497044")
				.programaResource(programaDiaResource())
				.build());
	}

	private void incluirEnquete(EnqueteService enqueteService, String codPrograma) {
		Collection<EnqueteRespostaResource> respostas = new ArrayList<>();
		incluirEnqueteResposta(respostas);
		enqueteService.incluir(EnqueteResource.builder().codPrograma(codPrograma).dataHoraFim("20/01/2020_10:30:00")
				.dataHoraInicio("20/01/2020_11:30:00").pergunta("O que está achando do programa de hoje ?")
				.programaDiaResource(programaDiaResource()).respostas(respostas).build());
	}

	private void incluirEnqueteResposta(Collection<EnqueteRespostaResource> respostas) {
		respostas.add(EnqueteRespostaResource.builder().resposta("Ótimo").build());
		respostas.add(EnqueteRespostaResource.builder().resposta("Bom").build());
		respostas.add(EnqueteRespostaResource.builder().resposta("Ruim").build());
		respostas.add(EnqueteRespostaResource.builder().resposta("Péssimo").build());
	}

	private void incluirProgramaDia(ProgramaDiaService programaDiaService) {
		programaDiaService.incluir(programaDiaResource());
	}

	private void incluirPrograma(ProgramaService programaService) {
		programaService.incluir(ProgramaResource.builder().avaliacaoPrograma(0).faixaEtaria(18).codigo("001")
				.nome("Fátima Bernardes")
				.descricao(
						"Comandado por Fátima Bernardes, o programa une informação, debate de temas atuais, música, dança, contando com a "
								+ "interação do público, especialistas.")
				.build());
	}

	private void incluirUsuariosPessoas(UsuarioService usuarioService, UsuarioConsultaService usuarioConsultaService) {
		try {
			byte[] avatarMasculino = imageToByte("/avatar/avatar_masculino.jfif");
			byte[] avatarFeminino = imageToByte("/avatar/avatar_masculino.jfif");
			usuarioService.incluir(UsuarioResource.builder().login("85655497042").senha("y|e8HTcAJ9").nome("Guilherme")
					.sobreNome("Costa Lopes").dataAniversario("06/07/1978").cidade("Belo Horizonte")
					.avatar(avatarMasculino).sexo("M").grupo("Administrador").build());
			
			usuarioService.incluir(UsuarioResource.builder().login("85655497041").senha("y|e8HTcAJ9").nome("João")
					.sobreNome("Costa Lopes").dataAniversario("06/07/1978").cidade("Belo Horizonte")
					.avatar(avatarMasculino).sexo("M").grupo("Administrador").build());
			
			usuarioService.incluir(UsuarioResource.builder().login("85655497043").senha("y|e8HTcAJ9").nome("Helena")
					.sobreNome("Costa Lopes").dataAniversario("06/07/1978").cidade("Belo Horizonte")
					.avatar(avatarFeminino).sexo("F").grupo("Administrador").build());
			
			usuarioService.incluir(UsuarioResource.builder().login("85655497044").senha("y|e8HTcAJ9").nome("Maria")
					.sobreNome("Costa Lopes").dataAniversario("06/07/1978").cidade("Belo Horizonte")
					.avatar(avatarFeminino).sexo("F").grupo("Administrador").build());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void incluirRegiao(RegiaoService regiaoService) {
		regiaoService.incluir(RegiaoResource.builder().nome("Sudeste").build());
	}

	private void incluirEstado(EstadoService service) {
		service.incluir(EstadoResource.builder().nome("Minas Gerais").regiao("Sudeste").abreviacao("MG").build());
	}

	private void incluirCidade(CidadeService cidadeService) {
		cidadeService.incluir(CidadeResource.builder().nome("Belo Horizonte").estado("Minas Gerais").build());
	}

	private void incluirGrupo(GrupoService grupoService) {
		grupoService.incluir(GrupoResource.builder().nome("Administrador").role("ROLE_ADMINISTRADOR").build());
	}

	private ProgramaDiaResource programaDiaResource() {
		return ProgramaDiaResource.builder().data("20/01/2020").diaSemana("QUINTA").horaInicio("08:00:00")
				.horaFim("11:30:00").programa("001").regiao("Sudeste").build();
	}
}
