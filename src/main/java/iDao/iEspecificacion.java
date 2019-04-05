package iDao;

import java.util.List;

import modelo.Especificacion;
import modelo.Proyecto;

/**
 * Esta interface define los metodos ha implementar en las classes para acceder a la base de datos para las especificaciones
 * @author: Jordi Jacas
 * @version: 1
 */


public interface iEspecificacion {
	
	void createEspecificacion(Especificacion especificacion);
	List<Especificacion> getAllEspecifiacionByProyecto(Proyecto proyecto);
	
}
