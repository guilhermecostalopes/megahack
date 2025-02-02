package br.com.megahack.core.programa;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de programa
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
@Table(name = "TAB_PROGRAMA", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "DS_CODIGO", "DS_NOME" }, name = "U1_TAB_PROGRAMA") }, indexes = {
				@Index(columnList = "DS_CODIGO", name = "I1_TAB_PROGRAMA"),
				@Index(columnList = "DS_NOME", name = "I2_TAB_PROGRAMA") })
public class Programa {

	@Id
	@Column(name = "PK_PROGRAMA")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@Column(name = "DS_CODIGO", nullable = false, length = 3)
	private String codigo;
	@Column(name = "DS_NOME", nullable = false, length = 300)
	private String nome;
	@Column(name = "DS_DESCRICAO", nullable = false, length = 5000)
	private String descricao;
	@Column(name = "NR_FAIXA_ETARIA", nullable = false)
	private Integer faixaEtaria;
	@Column(name = "NR_AVALIACAO_PROGRAMA", nullable = false)
	private Integer avaliacaoPrograma;
}
