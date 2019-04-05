package modelo;

import enumClass.userTypeEnum;

/**
 * Classe para guardar el usuario que esta connectado actualmente
 * @author: Jordi Jacas
 * @version: 1
 */

public final class UsuarioConectado {
	
	private static Usuario usuario;
	
    /**
     * Constructor por defecto
     */
    private UsuarioConectado () {
    }//Cierre del contructor
    
    /**
     * Metodos get y set de las variables
     */
	
	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		UsuarioConectado.usuario = usuario;
	}
	
	public static String getNombreUsuario() {
		return usuario.getNombre();
	}

	public static userTypeEnum getRolUsuario() {
		return usuario.getRol_usuario();
	}
}//Cierre de la clase 
