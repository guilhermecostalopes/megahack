package br.com.megahack.core.programadia;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.DATE;
import static lombok.AccessLevel.PROTECTED;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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

import br.com.megahack.core.enuns.DiaSemanaEnum;
import br.com.megahack.core.programa.Programa;
import br.com.megahack.core.regiao.Regiao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de dia por programação
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
@Table(name = "TAB_PROGRAMA_DIA", uniqueConstraints = { @UniqueConstraint(columnNames = { "FK_PROGRAMA",
		"ST_DIA_SEMANA", "FK_REGIAO", "HR_INICIO", "HR_FIM" }, name = "U1_TAB_PROGRAMA_DIA") }, indexes = {
				@Index(columnList = "FK_PROGRAMA", name = "I1_TAB_PROGRAMA_DIA"),
				@Index(columnList = "FK_REGIAO", name = "I2_TAB_PROGRAMA_DIA"),
				@Index(columnList = "ST_DIA_SEMANA", name = "I3_TAB_PROGRAMA_DIA") })
public class ProgramaDia {

	@Id
	@Column(name = "PK_PROGRAMA_DIA")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = Programa.class)
	@JoinColumn(name = "FK_PROGRAMA", nullable = false, foreignKey = @ForeignKey(name = "FOR_PROGRAMA"))
	private Programa programa;
	@ManyToOne(targetEntity = Regiao.class)
	@JoinColumn(name = "FK_REGIAO", nullable = false, foreignKey = @ForeignKey(name = "FOR_REGIAO"))
	private Regiao regiao;
	@Enumerated(STRING)
	@Column(name = "ST_DIA_SEMANA", nullable = false, length = 20)
	private DiaSemanaEnum diaSemana;
	@Temporal(DATE)
	@Column(name = "DT_DATA", nullable = false)
	private Date data;
	@Column(name = "HR_INICIO", nullable = false, length = 10)
	private String horaInicio;
	@Column(name = "HR_FIM", nullable = false, length = 10)
	private String horaFim;
}
