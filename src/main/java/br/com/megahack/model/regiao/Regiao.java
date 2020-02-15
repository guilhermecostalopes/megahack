package br.com.megahack.model.regiao;

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
 * Entidade de região
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
@Table(name = "TAB_REGIAO", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "DES_NOME" }, name = "U1_TAB_REGIAO") }, indexes = {
				@Index(columnList = "DES_NOME", name = "I1_TAB_REGIAO") })
public class Regiao extends Principal {

	private static final long serialVersionUID = 5941495122248041397L;

	@Id
	@Column(name = "PK_REGIAO")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@Column(name = "DES_NOME", nullable = false, length = 300)
	private String nome;
}
