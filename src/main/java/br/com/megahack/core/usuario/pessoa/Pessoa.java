package br.com.megahack.core.usuario.pessoa;

import static javax.persistence.TemporalType.DATE;
import static lombok.AccessLevel.PROTECTED;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import br.com.megahack.core.cidade.Cidade;
import br.com.megahack.core.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de pessoa
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
@Table(name = "TAB_PESSOA", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "DS_NOME" }, name = "U1_TAB_PESSOA") }, indexes = {
				@Index(columnList = "DS_NOME", name = "I1_TAB_PESSOA") })
public class Pessoa {

	@Id
	@Column(name = "PK_PESSOA")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@OneToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "FK_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "FOR_USUARIO"))
	private Usuario usuario;
	@Column(name = "DS_NOME", nullable = false, length = 100)
	private String nome;
	@Column(name = "DS_SOBRENOME", nullable = false, length = 300)
	private String senha;
	@Temporal(DATE)
	@Column(name = "DT_DATA_ANIVERSARIO", nullable = false)
	private Date dataAniversario;
	@ManyToOne(targetEntity = Cidade.class)
	@JoinColumn(name = "FK_CIDADE", nullable = false, foreignKey = @ForeignKey(name = "FOR_CIDADE"))
	private Cidade cidade;
}
