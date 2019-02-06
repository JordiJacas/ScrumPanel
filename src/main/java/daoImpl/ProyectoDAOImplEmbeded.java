package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import iDao.IProyecto;
import iDao.IUsuario;
import modelo.Proyecto;
import modelo.Usuario;

public class ProyectoDAOImplEmbeded implements IProyecto{

	Connection con;
	
	public Proyecto getProyectoByName(String nombre_proyecto) {
        connect();
        
        Proyecto proyecto = null;
        
		try {
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT p from Proyecto p where p.nombre_proyecto = '" + nombre_proyecto + "'");
			while (rs.next()) {
				Usuario prodOwn = new Usuario();
				Usuario scmMast = new Usuario();
				prodOwn.setUsuario_id(rs.getInt("productOwner_usuario_id"));
				scmMast.setUsuario_id(rs.getInt("scrumMaster_usuario_id"));
                proyecto = new Proyecto(rs.getString("nombre_proyecto"), 
                		rs.getString("descripcion"), prodOwn, scmMast,null);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return proyecto;
	}

	public void crearProyecto(Proyecto proyecto) {
		connect();
        
		try {
        	Statement stmt = con.createStatement();
        	stmt.executeUpdate("INSERT INTO `proyecto` (`proyecto_id`, `descripcion`, `nombre_proyecto`, `productOwner_usuario_id`, `scrumMaster_usuario_id`) VALUES (NULL, '" + proyecto.getDescripcion() +  "', '" + proyecto.getNombre_proyecto() +"', " + proyecto.getProductOwner().getUsuario_id() + ", " + proyecto.getScrumMaster().getUsuario_id() + ")");
		} catch (SQLException e) {
			System.out.println(e);
//			e.printStackTrace();
		}
		
		close();
	}
	
	public List<Proyecto> getAllProyectos() {
		connect();
        
        List<Proyecto> proyectos = new ArrayList<Proyecto>();
        
		try {
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Proyecto");
			while (rs.next()) {
				IUsuario userDao = new UsuarioDAOImplEmbebded();
				Usuario prodOwn = userDao.getUsuarioById(rs.getInt("productOwner_usuario_id"));
				Usuario scmMast = userDao.getUsuarioById(rs.getInt("scrumMaster_usuario_id"));
                Proyecto pro = new Proyecto(rs.getString("nombre_proyecto"), rs.getString("descripcion"), prodOwn, scmMast,null);
                pro.setProyecto_id(rs.getInt("proyecto_id"));
                proyectos.add(pro);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return proyectos;
	}
	
	public List<Proyecto> getProyectosByUser(Usuario usuario) {
		// TODO Auto-generated method stub
		connect();
        
        List<Proyecto> proyectos = new ArrayList<Proyecto>();
        
		try {
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Proyecto where nombre_usuario = '"+ usuario.getNombre_usuario() +"'");
			while (rs.next()) {
				Usuario prodOwn = new Usuario();
				Usuario scmMast = new Usuario();
				prodOwn.setUsuario_id(rs.getInt("productOwner_usuario_id"));
				scmMast.setUsuario_id(rs.getInt("scrumMaster_usuario_id"));
                
                proyectos.add(new Proyecto(rs.getString("nombre_proyecto"), 
                		rs.getString("descripcion"), prodOwn, scmMast,null));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return proyectos;
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
