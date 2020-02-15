package br.com.megahack.model.estado;

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

import br.com.megahack.model.regiao.Regiao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de estado
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
@Table(name = "TAB_ESTADO", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "FK_REGIAO", "DES_NOME" }, name = "U1_TAB_ESTADO") }, indexes = {
				@Index(columnList = "DES_NOME", name = "I1_TAB_ESTADO"),
				@Index(columnList = "FK_REGIAO", name = "I2_TAB_ESTADO") })
public class Estado {

	@Id
	@Column(name = "PK_ESTADO")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = Regiao.class)
	@JoinColumn(name = "FK_REGIAO", nullable = false, foreignKey = @ForeignKey(name = "FOR_REGIAO"))
	private Regiao regiao;
	@Column(name = "DES_NOME", nullable = false, length = 300)
	private String nome;
}
