package iDao;

import java.util.List;

import enumClass.userTypeEnum;
import modelo.Proyecto;
import modelo.Usuario;

/**
 * Esta interface define los metodos ha implementar en las classes para acceder a la base de datos para los usuarios
 * @author: Jordi Jacas
 * @version: 1
 */

public interface IUsuario {
	void crearUsuario(Usuario usuario) throws Exception;
	Usuario getUsuarioByNombreUsuario(String nombreUsuario);
	Usuario getUsuarioById(int id);
	List<Usuario> getUsuariosByRol(userTypeEnum rol);
	void updateUsuario(Usuario usuario, Proyecto proyectos);
}
