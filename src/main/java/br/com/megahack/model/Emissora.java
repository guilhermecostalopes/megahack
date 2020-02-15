package br.com.megahack.model;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de emissora
 * 
 * @author megahack
 */
@Setter
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Entity
// @UniqueConstraint - Não pode ter registros iguais
// @Index - Index do banco de dados
@Table(name = "TAB_EMISSORA", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "DS_NOME", "DS_CANAL", "FK_REGIAO" }, name = "U1_TAB_EMISSORA") }, indexes = {
				@Index(columnList = "DS_NOME", name = "I1_TAB_EMISSORA"),
				@Index(columnList = "DS_CANAL", name = "I2_TAB_EMISSORA"),
				@Index(columnList = "FK_REGIAO", name = "I3_TAB_EMISSORA") })
public class Emissora {

	@Id
	@Column(name = "PK_EMISSORA")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	// Se for uma emissoria filiada, este campo deve ser preenchido, por isso ele
	// não é obrigatório
	@ManyToOne(targetEntity = Emissora.class)
	@JoinColumn(name = "FK_EMISSORA", foreignKey = @ForeignKey(name = "FOR_EMISSORA"))
	private Emissora pai;
	@ManyToOne(targetEntity = Regiao.class)
	@JoinColumn(name = "FK_REGIAO", nullable = false, foreignKey = @ForeignKey(name = "FOR_REGIAO"))
	private Regiao regiao;
	@Column(name = "DS_NOME", nullable = false, length = 300)
	private String nome;
	@Column(name = "DS_CANAL", nullable = false)
	private Integer canal;
}
