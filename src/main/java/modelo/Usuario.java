package modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enumClass.userTypeEnum;


/**
 * Classe para definir un objecto usuario
 * @author: Jordi Jacas
 * @version: 1
 */

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int usuario_id;
	
	@Column(unique = true, nullable = false)
	private String nombre_usuario;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String password;
	
	@Column(unique = true)
	private String email;
	
	@ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<Proyecto> grupo_proyecto_id;
	
	@Column(nullable = false)
	private  userTypeEnum rol_usuario;
	
    /**
     * Constructor por defecto
     */

	public Usuario() {}//Cierre del contructor
	
    /**
     * Constructor con parametros
     * @param nombre_usuario - Nombre del usuario
     * @param nombre - Nombre real del usuario
     * @param password - Contraseņa 
     * @param email - Correo
     * @param rol_usuario - Tipo de usuario
     * @param grupo_proyecto_id - Grupo de proyecto en el que esta
     */

	public Usuario(String nombre_usuario, String nombre, String password, String email,
			userTypeEnum rol_usuario, List<Proyecto> grupo_proyecto_id) {
		super();
		this.nombre_usuario = nombre_usuario;
		this.nombre = nombre;
		this.password = password;
		this.email = email;
		this.grupo_proyecto_id = grupo_proyecto_id;
		this.rol_usuario = rol_usuario;
	}//Cierre del contructor
	
    /**
     * Constructor con parametros
     * @param nombre_usuario - Nombre del usuario
     * @param nombre - Nombre real del usuario
     * @param password - Contraseņa 
     * @param email - Correo
     * @param rol_usuario - Tipo de usuario
     */
	
	
	public Usuario(String nombre_usuario, String nombre, String password, String email,
			userTypeEnum rol_usuario) {
		super();
		this.nombre_usuario = nombre_usuario;
		this.nombre = nombre;
		this.password = password;
		this.email = email;
		this.grupo_proyecto_id = grupo_proyecto_id;
		this.rol_usuario = rol_usuario;
	}
	
    /**
     * Metodos get y set de las variables
     */

	public Usuario(int id) {
		this.usuario_id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Proyecto> getGrupo_proyecto_id() {
		return grupo_proyecto_id;
	}

	public void setGrupo_proyecto_id(List<Proyecto> grupo_proyecto_id) {
		this.grupo_proyecto_id = grupo_proyecto_id;
	}

	public userTypeEnum getRol_usuario() {
		return rol_usuario;
	}

	public void setRol_usuario(userTypeEnum rol_usuario) {
		this.rol_usuario = rol_usuario;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}	
}//Cierre de la clase 
