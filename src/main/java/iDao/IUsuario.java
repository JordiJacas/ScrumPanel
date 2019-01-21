package iDao;

import java.util.ArrayList;

import modelo.Usuario;

public interface IUsuario {
	void crearUsuario(Usuario usuario);
	Usuario getUsuarioByNombreUsuario(String nombreUsuario);
	ArrayList<Usuario> getUsarioAll();
}
