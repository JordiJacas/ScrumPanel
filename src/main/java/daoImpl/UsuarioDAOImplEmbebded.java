package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EnumType;

import enumClass.userTypeEnum;
import iDao.IUsuario;
import modelo.Proyecto;
import modelo.Usuario;

public class UsuarioDAOImplEmbebded implements IUsuario{

	Connection con;

	public void crearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		connect();
		String sql = "INSERT INTO `usuario` (`usuario_id`, `email`, `nombre`, `nombre_usuario`, `password`, `rol_usuario`) VALUES (" + usuario.getUsuario_id() + ", " + usuario.getEmail() + ", " + usuario.getNombre() + ", " + usuario.getNombre_usuario() + ", " + usuario.getPassword() + ", " + usuario.getRol_usuario() + ")";
    	try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	close();

	}

	public Usuario getUsuarioByNombreUsuario(String nombreUsuario) {
		connect();
		Usuario usuario = null;
        
		try {
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT u from Usuario u where u.nombre_usuario = '" + nombreUsuario + "'");
			while (rs.next()) {
                usuario = new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("password"), rs.getString("email"), userTypeEnum.valueOf(rs.getString("rol_usuario")));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return usuario;
	}

	public List<Usuario> getUsuariosByRol(userTypeEnum rol) {
		System.out.println("eeeey");
		connect();
		List<Usuario> usuarios = null;
        
		try {
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT u from Usuario u where u.rol_usuario = '" + rol.ordinal() + "'");
			while (rs.next()) {
                usuarios.add(new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("password"), rs.getString("email"), userTypeEnum.valueOf(rs.getString("rol_usuario"))));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return usuarios;
	}
	
	public void updateUsuario(Usuario usuario, Proyecto proyectos) {
		// TODO Auto-generated method stub
		
	}
	
	private void connect() {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:./data2.sqlite");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
