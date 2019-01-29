package iDao;

import java.util.List;

import enumClass.userTypeEnum;
import modelo.Proyecto;
import modelo.Usuario;

public interface IUsuario {
	void crearUsuario(Usuario usuario);
	Usuario getUsuarioByNombreUsuario(String nombreUsuario);
	List<Usuario> getUsuariosByRol(userTypeEnum rol);
	void updateUsuario(Usuario usuario, Proyecto proyectos);
}
