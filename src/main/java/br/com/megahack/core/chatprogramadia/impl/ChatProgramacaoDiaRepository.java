package br.com.megahack.core.chatprogramadia.impl;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.megahack.core.chatprogramadia.ChatProgramacaoDia;
import br.com.megahack.core.programadia.ProgramaDia;

@Repository
interface ChatProgramacaoDiaRepository extends CrudRepository<ChatProgramacaoDia, String> {

	Collection<ChatProgramacaoDia> findByProgramaDia(ProgramaDia programaDia);
}
