package br.com.megahack.model;

import static javax.persistence.TemporalType.TIMESTAMP;
import static lombok.AccessLevel.PROTECTED;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de programação por emissora
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
@Table(name = "TAB_ENQUETE", uniqueConstraints = { @UniqueConstraint(columnNames = { "FK_PROGRAMACAO_DIA",
		"DES_PERGUNTA" }, name = "U1_TAB_ENQUETE") }, indexes = {
				@Index(columnList = "FK_PROGRAMACAO_DIA", name = "I1_TAB_ENQUETE"),
				@Index(columnList = "DES_PERGUNTA", name = "I2_TAB_ENQUETE") })
public class Enquete {

	@Id
	@Column(name = "PK_ENQUETE")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = ProgramaDia.class)
	@JoinColumn(name = "FK_PROGRAMACAO_DIA", nullable = false, foreignKey = @ForeignKey(name = "FOR_PROGRAMACAO_DIA"))
	private ProgramaDia programaDia;
	@Column(name = "DES_PERGUNTA", nullable = false, length = 300)
	private String pergunta;
	@Temporal(TIMESTAMP)
	@Column(name = "TS_DATA_HORA_INICIO", nullable = false)
	private Date dataHoraInicio;
	@Temporal(TIMESTAMP)
	@Column(name = "TS_DATA_HORA_FIM", nullable = false)
	private Date dataHoraFim;
}
