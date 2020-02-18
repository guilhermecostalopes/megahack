package br.com.megahack.core.enqueteresposta;

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

import br.com.megahack.core.enquete.Enquete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de resposta da enquete
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
@Table(name = "TAB_ENQUETE_RESPOSTA", uniqueConstraints = { @UniqueConstraint(columnNames = { "FK_ENQUETE",
		"DES_RESPOSTA", "DES_CODIGO" }, name = "U1_TAB_ENQUETE_RESPOSTA") }, indexes = {
				@Index(columnList = "DES_CODIGO", name = "I1_TAB_ENQUETE_RESPOSTA"),
				@Index(columnList = "FK_ENQUETE", name = "I2_TAB_ENQUETE_RESPOSTA") })
public class EnqueteResposta {

	@Id
	@Column(name = "PK_ENQUETE_RESPOSTA")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = Enquete.class)
	@JoinColumn(name = "FK_ENQUETE", nullable = false, foreignKey = @ForeignKey(name = "FOR_ENQUETE"))
	private Enquete enquete;
	@Column(name = "DES_CODIGO", nullable = false, length = 3)
	private String codigo;
	@Column(name = "DES_RESPOSTA", nullable = false, length = 300)
	private String resposta;
	// Deve iniciar com 0, para resposta inserida
	@Column(name = "NR_VOTACAO_FAVOR", nullable = false)
	private Integer votacaoFavor;
	// Deve iniciar com 0, para resposta inserida
	@Column(name = "NR_VOTACAO_CONTRA", nullable = false)
	private Integer votacaoContra;
}
