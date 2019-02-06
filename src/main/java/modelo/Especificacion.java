package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="especificacion")
public class Especificacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String descripcion;
	
	@ManyToOne
	private Proyecto proyecto_id;
	
	public Especificacion() {}
	
	public Especificacion(String descripcion, Proyecto proyecto_id) {
		super();
		this.descripcion = descripcion;
		this.proyecto_id = proyecto_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Proyecto getProyecto_id() {
		return proyecto_id;
	}

	public void setProyecto_id(Proyecto proyecto_id) {
		this.proyecto_id = proyecto_id;
	}
	
	

}
