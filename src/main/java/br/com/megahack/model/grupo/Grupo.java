package br.com.megahack.model.grupo;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import br.com.ghsistemas.principal.core.modelo.Principal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de grupo
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
@Table(name = "TAB_GRUPO", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "DS_NOME" }, name = "U1_TAB_GRUPO") }, indexes = {
				@Index(columnList = "DS_NOME", name = "I1_TAB_GRUPO") })
public class Grupo extends Principal {

	private static final long serialVersionUID = 3050889805256653056L;

	@Id
	@Column(name = "PK_GRUPO")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@Column(name = "DS_NOME", nullable = false, length = 100)
	private String nome;
	@Column(name = "DS_ROLE", nullable = false, length = 100)
	private String role;
}
