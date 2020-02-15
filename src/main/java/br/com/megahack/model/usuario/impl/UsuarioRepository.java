package br.com.megahack.model.usuario.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.usuario.Usuario;

@Repository
interface UsuarioRepository extends CrudRepository<Usuario, String> {

	Usuario findByLogin(String login);
}
