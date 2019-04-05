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

/**
* Esta clase contiene los metodos para acceder a la base de datos en bebiba y ejecutar la sentecia deseada para las especificaciones
* @author: Jordi Jacas
* @version: 1
*/

public class EspecifiacionDAOImplEmbebded implements iEspecificacion{

	Connection con;
	
    /**
     * Metodo para insertar una especificacion en la base de datos
     * @param especificacion 
     */

	public void createEspecificacion(Especificacion especificacion) {
		// TODO Auto-generated method stub
		connect();
		try {
        	Statement stmt = con.createStatement();
        	stmt.executeUpdate("INSERT INTO `Especificacion` (`descripcion`, `proyecto_id_proyecto_id`) VALUES ('" + especificacion.getDescripcion() + "', '" + especificacion.getProyecto_id() + "')");
		} catch (SQLException e) {
			System.out.println(e);
//			e.printStackTrace();
		}
		close();
	}//Cierre de metodo
	
    /**
     * Metodo que busca en la base de datos todas las especificaciones de un proyecto esecifico
     * @param proyecto 
     * @return Una lista de todas las especificaciones
     */

	public List<Especificacion> getAllEspecifiacionByProyecto(Proyecto proyecto) {
		connect();
		List<Especificacion> especs = new ArrayList<Especificacion>();
		try {
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Especificacion where proyecto_id_proyecto_id = '" + proyecto.getProyecto_id() + "'");
			while (rs.next()) {
                especs.add(new Especificacion(rs.getString("descripcion"), proyecto));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		
		return especs;
	}//Cierre del metodo
	
	/**
     * Metodo que conecta a la base de datos
     */
	
	private void connect() {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:./data2.sqlite");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//Cierre del metodo
	
	/**
     * Metodo que cierra la coneccion de la base de datos
     */
	
	private void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
