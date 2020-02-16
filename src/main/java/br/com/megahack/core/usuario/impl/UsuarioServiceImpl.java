package br.com.megahack.core.usuario.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.megahack.core.cidade.Cidade;
import br.com.megahack.core.cidade.CidadeConsultaService;
import br.com.megahack.core.pessoa.Pessoa;
import br.com.megahack.core.usuario.Usuario;
import br.com.megahack.core.usuario.UsuarioService;
import br.com.megahack.core.usuario.resource.UsuarioResource;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CidadeConsultaService cidadeConsultaService;
	
	@Override
	public UsuarioResource incluir(UsuarioResource resource) {
		String senha = passwordEncoder.encode(resource.getSenha());
		
		Usuario usuario = Usuario.builder().login(resource.getLogin()).senha(senha).avatar(resource.getAvatar()).build();
		
		Cidade cidade = cidadeConsultaService.buscarPorNome(resource.getCidade());
		Pessoa pessoa = Pessoa.builder().nome(resource.getNome()).sobreNome(resource.getSobreNome()).cidade(cidade).dataAniversario(new Date()).build();
		usuario.setPessoa(pessoa);
		
		usuario = repository.save(usuario);
		alterarResource(resource, usuario);
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
		resource.setSenha(null);
	}
}
