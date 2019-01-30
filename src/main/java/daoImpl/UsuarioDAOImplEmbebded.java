package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;

import org.hibernate.mapping.Array;

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
			ResultSet rs = stmt.executeQuery("SELECT * from Usuario where nombre_usuario = '" + nombreUsuario + "'");
			while (rs.next()) {
                usuario = new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("password"), rs.getString("email"), userTypeEnum.values()[rs.getInt("rol_usuario")]);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return usuario;
	}

	public List<Usuario> getUsuariosByRol(userTypeEnum rol) {
		//System.out.println("eeeey");
		connect();
		List<Usuario> usuarios = new ArrayList<Usuario>();
        
		try {
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Usuario where rol_usuario = '"+rol.ordinal()+"';");
			while (rs.next()) {
                usuarios.add(new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("password"), rs.getString("email"), userTypeEnum.values()[rs.getInt("rol_usuario")]));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		
		System.out.println(usuarios);
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
