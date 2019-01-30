package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import iDao.IProyecto;
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
                		rs.getString("descripcion"), prodOwn, scmMast);
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
        	stmt.executeUpdate("INSERT INTO `proyecto` (`proyecto_id`, `descripcion`, `nombre_proyecto`, `productOwner_usuario_id`, `scrumMaster_usuario_id`) VALUES (NULL, " + proyecto.getDescripcion() +  ", " + proyecto.getNombre_proyecto() +", " + proyecto.getProductOwner().getUsuario_id() + ", " + proyecto.getScrumMaster().getUsuario_id() + ")");
		} catch (SQLException e) {
			System.out.println(e);
//			e.printStackTrace();
		}
		
		close();
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
