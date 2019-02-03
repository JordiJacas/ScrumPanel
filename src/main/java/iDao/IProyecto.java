package iDao;

import java.util.List;

import modelo.Proyecto;
import modelo.Usuario;

public interface IProyecto {
	
	Proyecto getProyectoByName(String nombre_proyecto);
	void crearProyecto(Proyecto proyecto);
	List<Proyecto> getAllProyectos();
	List<Proyecto> getProyectosByUser(Usuario usuario);
	
}
