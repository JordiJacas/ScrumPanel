package iDao;

import modelo.Proyecto;

public interface IProyecto {
	
	Proyecto getProyectoByName(String nombre_proyecto);
	void crearProyecto(Proyecto proyecto);
	
}
