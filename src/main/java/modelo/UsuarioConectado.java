package modelo;

import enumClass.userTypeEnum;

public final class UsuarioConectado {
	
	private static Usuario usuario;
	
    private UsuarioConectado () {
    }
	
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
}
