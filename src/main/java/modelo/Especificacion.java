package modelo;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe para definir un objecto especificacion
 * @author: Jordi Jacas
 * @version: 1
 */

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
	
    /**
     * Constructor por defecto
     */

	public Especificacion() {}//Cierre del constructor
	
    /**
     * Constructor con parametros
     * @param descripcion - Breve descripcion de la especificacion
     * @param proyecto_id - Proyecto en la que esta asociada la especificacion
     */

	public Especificacion(String descripcion, Proyecto proyecto_id) {
		super();
		this.descripcion = descripcion;
		this.proyecto_id = proyecto_id;
	}//Cierre del constructor
	
    /**
     * Metodos get y set de las variables
     */

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
	}//Cierre del método
	
}//Cierre de la clase 
