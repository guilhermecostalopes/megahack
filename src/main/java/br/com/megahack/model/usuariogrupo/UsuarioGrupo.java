package br.com.megahack.model.usuariogrupo;

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

import br.com.megahack.model.grupo.Grupo;
import br.com.megahack.model.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de usuário x grupo
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
@Table(name = "TAB_USUARIO_GRUPO", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "FK_USUARIO", "FK_GRUPO" }, name = "U1_TAB_USUARIO_GRUPO") }, indexes = {
				@Index(columnList = "FK_USUARIO", name = "I1_TAB_USUARIO_GRUPO"),
				@Index(columnList = "FK_GRUPO", name = "I2_TAB_USUARIO_GRUPO") })
public class UsuarioGrupo {

	@Id
	@Column(name = "PK_USUARIO_GRUPO")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "FK_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "FOR_USUARIO"))
	private Usuario usuario;
	@ManyToOne(targetEntity = Grupo.class)
	@JoinColumn(name = "FK_GRUPO", nullable = false, foreignKey = @ForeignKey(name = "FOR_GUPO"))
	private Grupo grupo;
}
