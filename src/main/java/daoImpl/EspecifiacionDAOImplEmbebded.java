package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import enumClass.userTypeEnum;
import iDao.iEspecificacion;
import modelo.Especificacion;
import modelo.Proyecto;
import modelo.Usuario;

public class EspecifiacionDAOImplEmbebded implements iEspecificacion{

	Connection con;
	
	public void createEspecificacion(Especificacion especificacion) {
		// TODO Auto-generated method stub
		
	}

	public List<Especificacion> getAllEspecifiacionByProyecto(Proyecto proyecto) {
		connect();
		List<Especificacion> especs = new ArrayList<Especificacion>();
        System.out.println(proyecto.getProyecto_id());
		try {
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Especificacion where proyecto_id_proyecto_id = '1';");
			while (rs.next()) {
                especs.add(new Especificacion(rs.getString("descripcion"), proyecto));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		
		return especs;
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
