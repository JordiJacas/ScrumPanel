package daoTest;

import javax.persistence.EnumType;

import enumClass.userTypeEnum;
import modelo.Usuario;
import modelo.UsuarioConectado;

public class EnumTypeUsuarioTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(userTypeEnum.USER_ADMINISTRATOR.ordinal());
		
		System.out.println(UsuarioConectado.getUsuario());
		
		UsuarioConectado.setUsuario(new Usuario("nombre_usuario"," String nombre", "String contraseña", "String email",
				userTypeEnum.USER_ADMINISTRATOR));
		
		System.out.println(UsuarioConectado.getUsuario());
	}

}
