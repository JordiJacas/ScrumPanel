package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="proyecto")
public class Proyecto {
	
	@Id
	@GeneratedValue
	private int proyecto_id;
	
	@Column
	private String nombre_proyecto;
	
	@Column
	private String descripcion;
	
	@ManyToOne
	private Usuario scrumMaster;
	
	@ManyToOne
	private Usuario productOwner;
	
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
	
	

}
