package modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@ManyToOne
	private GrupoUsuario grupo_usuario_id;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Especificacion> especificacion_id;
	
	public Proyecto() {}

	public Proyecto(String nombre_proyecto, String descripcion, Usuario scrumMaster, Usuario productOwner) {
		super();
		this.nombre_proyecto = nombre_proyecto;
		this.descripcion = descripcion;
		this.scrumMaster = scrumMaster;
		this.productOwner = productOwner;
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

	public GrupoUsuario getGrupo_usuario_id() {
		return grupo_usuario_id;
	}

	public void setGrupo_usuario_id(GrupoUsuario grupo_usuario_id) {
		this.grupo_usuario_id = grupo_usuario_id;
	}
	
	
}
