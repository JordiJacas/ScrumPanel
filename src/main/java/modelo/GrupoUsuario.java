package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="grupo_usuario")
public class GrupoUsuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany
	private List<Usuario> usuario_id;
	
	@ManyToOne
	private Proyecto proyecto_id;
	
	public GrupoUsuario() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Usuario> getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(List<Usuario> usuario_id) {
		this.usuario_id = usuario_id;
	}

	public Proyecto getProyecto_id() {
		return proyecto_id;
	}

	public void setProyecto_id(Proyecto proyecto_id) {
		this.proyecto_id = proyecto_id;
	}
	
	
}
