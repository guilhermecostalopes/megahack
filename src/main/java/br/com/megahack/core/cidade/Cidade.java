package br.com.megahack.core.cidade;

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

import br.com.megahack.core.estado.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de cidade
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
@Table(name = "TAB_CIDADE", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "FK_ESTADO", "DES_NOME" }, name = "U1_TAB_CIDADE") }, indexes = {
				@Index(columnList = "FK_ESTADO", name = "I1_TAB_CIDADE"),
				@Index(columnList = "DES_NOME", name = "I2_TAB_CIDADE") })
public class Cidade {

	@Id
	@Column(name = "PK_CIDADE")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = Estado.class)
	@JoinColumn(name = "FK_ESTADO", nullable = false, foreignKey = @ForeignKey(name = "FOR_ESTADO"))
	private Estado estado;
	@Column(name = "DES_NOME", nullable = false, length = 300)
	private String nome;
}
