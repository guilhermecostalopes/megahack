package br.com.megahack.core.chatprogramadia;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import br.com.megahack.core.programadia.ProgramaDia;
import br.com.megahack.core.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de chat por programadia
 * 
 * @author megahack
 */
@Setter
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Entity
//@UniqueConstraint - Não pode ter registros iguais
//@Index - Index do banco de dados
@Table(name = "TAB_CHAT_PROGRAMACAO_DIA", indexes = {
		@Index(columnList = "FK_USUARIO", name = "I1_TAB_CHAT_PROGRAMACAO_DIA"),
		@Index(columnList = "FK_PROGRAMACAO_DIA", name = "I2_TAB_CHAT_PROGRAMACAO_DIA") })
public class ChatProgramacaoDia {

	@Id
	@Column(name = "PK_CHAT_PROGRAMACAO_DIA")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "FK_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "FOR_USUARIO"))
	private Usuario usuario;
	@ManyToOne(targetEntity = ProgramaDia.class)
	@JoinColumn(name = "FK_PROGRAMACAO_DIA", nullable = false, foreignKey = @ForeignKey(name = "FOR_PROGRAMACAO_DIA"))
	private ProgramaDia programaDia;
	@Column(name = "DES_TEXTO", nullable = false, length = 50000)
	private String texto;
}
