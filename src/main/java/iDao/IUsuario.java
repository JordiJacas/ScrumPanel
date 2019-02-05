package iDao;

import java.util.List;

import enumClass.userTypeEnum;
import modelo.Proyecto;
import modelo.Usuario;

public interface IUsuario {
	void crearUsuario(Usuario usuario) throws Exception;
	Usuario getUsuarioByNombreUsuario(String nombreUsuario);
	Usuario getUsuarioById(int id);
	List<Usuario> getUsuariosByRol(userTypeEnum rol);
	void updateUsuario(Usuario usuario, Proyecto proyectos);
}
