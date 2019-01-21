package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	private int usuario_id;
	
	@Column
	private String nombre_usuario;
	
	@Column
	private String nombre;
	
	@Column
	private String contrase�a;
	
	@Column
	private String email;
	
	@Column
	private Proyecto grupo_proyecto_id;
	
	@Column
	private GrupoProyecto grupo_usuario_id;

	public Usuario(int usuario_id, String nombre_usuario, String nombre, String contrase�a, String email,
			Proyecto grupo_proyecto_id, GrupoProyecto grupo_usuario_id) {
		super();
		this.usuario_id = usuario_id;
		this.nombre_usuario = nombre_usuario;
		this.nombre = nombre;
		this.contrase�a = contrase�a;
		this.email = email;
		this.grupo_proyecto_id = grupo_proyecto_id;
		this.grupo_usuario_id = grupo_usuario_id;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Proyecto getGrupo_proyecto_id() {
		return grupo_proyecto_id;
	}

	public void setGrupo_proyecto_id(Proyecto grupo_proyecto_id) {
		this.grupo_proyecto_id = grupo_proyecto_id;
	}

	public GrupoProyecto getGrupo_usuario_id() {
		return grupo_usuario_id;
	}

	public void setGrupo_usuario_id(GrupoProyecto grupo_usuario_id) {
		this.grupo_usuario_id = grupo_usuario_id;
	}

	public int getUsuario_id() {
		return usuario_id;
	}
	
	
}
