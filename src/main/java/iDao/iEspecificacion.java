package iDao;

import java.util.List;

import modelo.Especificacion;
import modelo.Proyecto;

public interface iEspecificacion {
	List<Especificacion> getAllEspecifiacion(Proyecto proyecto);
	List<Especificacion> getAllEspecifiacionByProyecto(Proyecto proyecto);
}
