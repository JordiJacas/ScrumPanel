package iDao;

import java.util.List;

import modelo.Especificacion;
import modelo.Proyecto;

public interface iEspecificacion {
	
	void createEspecificacion(Especificacion especificacion);
	List<Especificacion> getAllEspecifiacionByProyecto(Proyecto proyecto);
	
}
