package daoTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EnumType;

import enumClass.userTypeEnum;
import modelo.Usuario;
import modelo.UsuarioConectado;

public class EnumTypeUsuarioTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(userTypeEnum.USER_ADMINISTRATOR.ordinal());
		Connection con;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlite:./data2.sqlite");
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Usuario where rol_usuario = 2;");
			while (rs.next()) {
                //usuarios.add(new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("password"), rs.getString("email"), userTypeEnum.valueOf(rs.getString("rol_usuario"))));
				System.out.println(rs.getString("nombre_usuario") + " + " + rs.getInt("rol_usuario"));
				if(userTypeEnum.USER_ADMINISTRATOR.ordinal() == Integer.parseInt(rs.getString("rol_usuario")) ) {
					//System.out.println(userTypeEnum.SCRUM_MASTER);
				}
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
