package br.com.megahack.core.usuario.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.usuario.Usuario;

@Repository
interface UsuarioRepository extends CrudRepository<Usuario, String> {

	Usuario findByLogin(String login);

	@Query(value = "SELECT u FROM Usuario u WHERE (UPPER(u.login) LIKE :login OR :login is null) ")
	Page<Usuario> findByLoginLike(String login, Pageable pageable);

}
