package br.com.megahack.core.emissoraprogramacaodia;

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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import br.com.megahack.core.emissora.Emissora;
import br.com.megahack.core.programadia.ProgramaDia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de emissora x dia da programação
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
@Table(name = "TAB_EMISSORA_PROGRAMACAO_DIA", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"FK_PROGRAMA_DIA", "FK_EMISSORA" }, name = "U1_TAB_EMISSORA_PROGRAMACAO_DIA") }, indexes = {
				@Index(columnList = "FK_PROGRAMA_DIA", name = "I1_TAB_EMISSORA_PROGRAMACAO"),
				@Index(columnList = "FK_EMISSORA", name = "I2_TAB_EMISSORA_PROGRAMACAO") })
public class EmissoraProgramacaoDia {

	@Id
	@Column(name = "PK_EMISSORA_PROGRAMACAO_DIA")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = ProgramaDia.class)
	@JoinColumn(name = "FK_PROGRAMA_DIA", nullable = false, foreignKey = @ForeignKey(name = "FOR_PROGRAMA_DIA"))
	private ProgramaDia programaDia;
	@ManyToOne(targetEntity = Emissora.class)
	@JoinColumn(name = "FK_EMISSORA", nullable = false, foreignKey = @ForeignKey(name = "FOR_EMISSORA"))
	private Emissora emissora;
}
