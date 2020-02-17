package br.com.megahack.core.chatprogramadia.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.chatprogramadia.ChatProgramacaoDia;

@Repository
interface ChatProgramacaoDiaRepository extends CrudRepository<ChatProgramacaoDia, String> {

}
