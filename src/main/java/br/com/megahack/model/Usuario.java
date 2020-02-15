package br.com.megahack.model;

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
 * Entidade de usuário
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
@Table(name = "TAB_USUARIO", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "DS_LOGIN" }, name = "U1_TAB_USUARIO") }, indexes = {
				@Index(columnList = "DS_LOGIN", name = "I1_TAB_USUARIO") })
public class Usuario {

	@Id
	@Column(name = "PK_USUARIO")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	// O login deve ser o email
	@Column(name = "DS_LOGIN", nullable = false, length = 300)
	private String login;
	@Column(name = "DS_SENHA", nullable = false, length = 300)
	private String senha;
	@Column(name = "IM_AVATAR", nullable = false, length = 300)
	private byte[] avatar;
}
