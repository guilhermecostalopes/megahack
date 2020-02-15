package br.com.megahack.model.emissorausuario.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.model.emissorausuario.EmissoraUsuario;

@Repository
interface EmissoraUsuarioRepository extends CrudRepository<EmissoraUsuario, String> {

}
