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
 * Entidade de emissorias x usuários
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
@Table(name = "TAB_EMISSORA_USUARIO", uniqueConstraints = { @UniqueConstraint(columnNames = { "FK_USUARIO",
		"FK_EMISSORA" }, name = "U1_TAB_EMISSORA_USUARIO") }, indexes = {
				@Index(columnList = "FK_USUARIO", name = "I1_TAB_EMISSORA_USUARIO"),
				@Index(columnList = "FK_EMISSORA", name = "I2_TAB_EMISSORA_USUARIO") })
public class EmissorasUsuarios {

	@Id
	@Column(name = "PK_EMISSORA_USUARIO")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "FK_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "FOR_USUARIO"))
	private Usuario usuario;
	@ManyToOne(targetEntity = Emissora.class)
	@JoinColumn(name = "FK_EMISSORA", nullable = false, foreignKey = @ForeignKey(name = "FOR_EMISSORA"))
	private Emissora emissora;
}
