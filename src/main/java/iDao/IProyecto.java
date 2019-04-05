package iDao;

import java.util.List;

import modelo.Proyecto;
import modelo.Usuario;

/**
 * Esta interface define los metodos ha implementar en las classes para acceder a la base de datos para los proyectos
 * @author: Jordi Jacas
 * @version: 1
 */

public interface IProyecto {
	
	Proyecto getProyectoByName(String nombre_proyecto);
	void crearProyecto(Proyecto proyecto) throws Exception;
	List<Proyecto> getAllProyectos();
	List<Proyecto> getProyectosByUser(Usuario usuario);
	
}
