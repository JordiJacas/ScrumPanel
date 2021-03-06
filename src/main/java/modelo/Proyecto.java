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

/**
 * Classe para definir un objecto proyecto
 * @author: Jordi Jacas
 * @version: 1
 */

@Entity
@Table(name="proyecto")
public class Proyecto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int proyecto_id;
	
	@Column(unique = true, nullable = false)
	private String nombre_proyecto;
	
	@Column
	private String descripcion;
	
	@ManyToOne
	private Usuario scrumMaster;
	
	@ManyToOne
	private Usuario productOwner;
	
	@ManyToMany
	private List<Usuario> usuario_id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Especificacion> especificacion_id;
	
	/**
     * Constructor por defecto
     */
	
	public Proyecto() {}//Cierre del contructor
	
    /**
     * Constructor con parametros
     * @param nombre_proyecto
     * @param descripcion
     * @param scrumMaster
     * @param productOwner
     * @param especificaciones
     */
	
	public Proyecto(String nombre_proyecto, String descripcion, Usuario scrumMaster, Usuario productOwner, List<Especificacion> especificaciones) {
		this.nombre_proyecto = nombre_proyecto;
		this.descripcion = descripcion;
		this.scrumMaster = scrumMaster;
		this.productOwner = productOwner;
		this.especificacion_id = especificaciones;
	}//Cierre del contructor
	
    /**
     * Constructor con parametros
     * @param nombre_proyecto
     * @param descripcion
     * @param scrumMaster
     * @param productOwner
     * @param especificaciones
     */

	public Proyecto(int proyecto_id, String nombre_proyecto, String descripcion, Usuario scrumMaster, Usuario productOwner, List<Especificacion> especificaciones) {
		this.proyecto_id = proyecto_id;
		this.nombre_proyecto = nombre_proyecto;
		this.descripcion = descripcion;
		this.scrumMaster = scrumMaster;
		this.productOwner = productOwner;
		this.especificacion_id = especificaciones;
	}//Cierre del contructor
	
	/**
     * Metodos get y set de las variables
     */

	public Proyecto(int id) {
		this.proyecto_id = id;
	}

	public String getNombre_proyecto() {
		return nombre_proyecto;
	}

	public void setNombre_proyecto(String nombre_proyecto) {
		this.nombre_proyecto = nombre_proyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(Usuario scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

	public Usuario getProductOwner() {
		return productOwner;
	}

	public void setProductOwner(Usuario productOwner) {
		this.productOwner = productOwner;
	}

	public int getProyecto_id() {
		return proyecto_id;
	}
	
	public void setProyecto_id(int proyecto_id) {
		this.proyecto_id = proyecto_id;
	}

	public List<Especificacion> getEspecificacion_id() {
		return especificacion_id;
	}

	public void setEspecificacion_id(List<Especificacion> especificacion_id) {
		this.especificacion_id = especificacion_id;
	}

	public List<Usuario> getGrupo_usuario_id() {
		return usuario_id;
	}

	public void setGrupo_usuario_id(List<Usuario> grupo_usuario_id) {
		this.usuario_id = grupo_usuario_id;
	}
	
	
}
