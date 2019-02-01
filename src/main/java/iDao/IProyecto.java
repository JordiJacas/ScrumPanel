package iDao;

import java.util.List;

import modelo.Proyecto;

public interface IProyecto {
	
	Proyecto getProyectoByName(String nombre_proyecto);
	void crearProyecto(Proyecto proyecto);
	List<Proyecto> getAllProyectos();
	
}
