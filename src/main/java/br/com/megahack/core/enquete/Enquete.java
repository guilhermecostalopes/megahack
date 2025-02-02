package br.com.megahack.core.enquete;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.TemporalType.TIMESTAMP;
import static lombok.AccessLevel.PROTECTED;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import br.com.megahack.core.enqueteresposta.EnqueteResposta;
import br.com.megahack.core.programadia.ProgramaDia;
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
@Table(name = "TAB_ENQUETE", uniqueConstraints = { @UniqueConstraint(columnNames = { "FK_PROGRAMA_DIA", "DES_PERGUNTA",
		"DES_CODIGO" }, name = "U1_TAB_ENQUETE") }, indexes = {
				@Index(columnList = "FK_PROGRAMA_DIA", name = "I1_TAB_ENQUETE"),
				@Index(columnList = "DES_PERGUNTA", name = "I2_TAB_ENQUETE"),
				@Index(columnList = "DES_CODIGO", name = "I3_TAB_ENQUETE") })
public class Enquete {

	@Id
	@Column(name = "PK_ENQUETE")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = ProgramaDia.class)
	@JoinColumn(name = "FK_PROGRAMA_DIA", nullable = false, foreignKey = @ForeignKey(name = "FOR_PROGRAMA_DIA"))
	private ProgramaDia programaDia;
	@Column(name = "DES_CODIGO", nullable = false, length = 300)
	private String codigo;
	@Column(name = "DES_PERGUNTA", nullable = false, length = 300)
	private String pergunta;
	@Temporal(TIMESTAMP)
	@Column(name = "TS_DATA_HORA_INICIO", nullable = false)
	private Date dataHoraInicio;
	@Temporal(TIMESTAMP)
	@Column(name = "TS_DATA_HORA_FIM", nullable = false)
	private Date dataHoraFim;
	// Quando salvar uma enquete deve ter no minimo duas respostas
	@OneToMany(mappedBy = "enquete", targetEntity = EnqueteResposta.class, cascade = { PERSIST, MERGE, REFRESH,
			DETACH })
	private Collection<EnqueteResposta> enqueteResposta;
}
