package br.com.megahack.core.usuario.impl;

import static br.com.megahack.Converter.imageToByte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.megahack.core.cidade.Cidade;
import br.com.megahack.core.cidade.CidadeConsultaService;
import br.com.megahack.core.grupo.Grupo;
import br.com.megahack.core.grupo.GrupoConsultaService;
import br.com.megahack.core.pessoa.Pessoa;
import br.com.megahack.core.usuario.Usuario;
import br.com.megahack.core.usuario.UsuarioService;
import br.com.megahack.core.usuario.resource.UsuarioResource;
import br.com.megahack.core.usuariogrupo.UsuarioGrupo;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CidadeConsultaService cidadeConsultaService;
	@Autowired
	private GrupoConsultaService grupoConsultaService;

	@Override
	public UsuarioResource incluir(UsuarioResource resource) {
		try {
			byte[] avatar;
			if (resource.getAvatar() == null) {
				if (resource.getSexo().equals("M")) {
					avatar = imageToByte("/avatar/avatar_masculino.jfif");
				} else {
					avatar = imageToByte("/avatar/avatar_masculino.jfif");
				}
			} else {
				avatar = resource.getAvatar();
			}
			String senha = passwordEncoder.encode(resource.getSenha());
			Usuario usuario = Usuario.builder().login(resource.getLogin()).senha(senha).avatar(avatar)
					.sexo(resource.getSexo()).build();

			Cidade cidade = cidadeConsultaService.buscarPorNome(resource.getCidade());
			Pessoa pessoa = Pessoa.builder().nome(resource.getNome()).sobreNome(resource.getSobreNome()).cidade(cidade)
					.dataAniversario(new Date()).build();
			pessoa.setUsuario(usuario);
			usuario.setPessoa(pessoa);

			Grupo grupo = grupoConsultaService.buscarPorNome(resource.getGrupo());
			Collection<UsuarioGrupo> usuariosGrupos = new ArrayList<>();
			usuariosGrupos.add(UsuarioGrupo.builder().grupo(grupo).usuario(usuario).build());
			usuario.setUsuariosGrupos(usuariosGrupos);

			usuario = repository.save(usuario);
			alterarResource(resource, usuario);

		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		return resource;
	}

	@Override
	public UsuarioResource alterar(UsuarioResource resource) {
		Usuario usuarioAtual = repository.findById(resource.getId()).orElse(null);
		usuarioAtual.setLogin(resource.getLogin());
		usuarioAtual.setAvatar(resource.getAvatar());
		Usuario usuario = repository.save(usuarioAtual);
		alterarResource(resource, usuario);
		return resource;
	}

	@Override
	public UsuarioResource excluir(UsuarioResource resource) {
		Usuario usuario = repository.findById(resource.getId()).orElse(null);
		// usuario.setStatusDoRegistro(EXCLUIDO);
		repository.delete(usuario);
		alterarResource(resource, usuario);
		return resource;
	}

	private void alterarResource(UsuarioResource resource, Usuario usuario) {
		resource.setAvatar(usuario.getAvatar());
		resource.setId(usuario.getId());
		resource.setLogin(usuario.getLogin());
		resource.setSexo(usuario.getSexo());
		resource.setSenha(null);
	}
}
