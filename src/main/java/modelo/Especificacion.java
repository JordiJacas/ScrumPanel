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
	
	@Column(nullable=false)
	private int sprint;
	
	@ManyToOne
	@JoinColumn(name="proyecto_proyecto_id", nullable=false)
	private Proyecto proyecto_id;
	
	public Especificacion() {}
	
	public Especificacion(String descripcion, int sprint, Proyecto proyecto_id) {
		super();
		this.descripcion = descripcion;
		this.sprint = sprint;
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

	public int getSprint() {
		return sprint;
	}

	public void setSprint(int sprint) {
		this.sprint = sprint;
	}

	public Proyecto getProyecto_id() {
		return proyecto_id;
	}

	public void setProyecto_id(Proyecto proyecto_id) {
		this.proyecto_id = proyecto_id;
	}
	
	

}
