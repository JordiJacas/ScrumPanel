package config;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import daoImpl.ProyectoDAOImpl;
import daoImpl.UsuarioDAOImpl;
import daoImpl.UsuarioDAOImplEmbebded;
import enumClass.userTypeEnum;
import iDao.IProyecto;
import iDao.IUsuario;
import modelo.Proyecto;
import modelo.Usuario;


/**
 * 
 * Clase que se encarga de gestionar la conexion con la Base de Datos
 * 
 * @author jaimepm
 * 
 */

public class ConnnectDBDao {
	
	//private static String state = "ONLINE";
	private static boolean state = true;
	
	/**
	 * Getter del estado de la conexion (si esta conectado o no lo esta)
	 * @return state
	 */
	public boolean getState() {
		return this.state;
	}
	
	/**
	 * 
	 * @return state's string (ONLINE / OFFLINE)
	 */
	public String getStateString() {
		
		if(this.state) return "ONLINE";
		if(!this.state) return "OFFLINE";
		
		return null;
	}
	
	
	/**
	 * Constructor que cambia la variable state y ejecuta las querys en la bbdd remota si esta online
	 */
	public ConnnectDBDao() {
		if (!connectRemoteDB()) state = false;
		else insertChanges();
	}
	
	/**
	 * Trata de conectar con la bbdd
	 * @return true si se ha conectado, false si no se ha conectado
	 */
	public boolean connectRemoteDB() {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
			EntityManager entityManager = factory.createEntityManager();
			entityManager.close();
			factory.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * Se encarga de replicar las querys ejecutadas en la bbdd embebida en la remota
	 */
	public void insertChanges() {
		fileOffline fileManager = new fileOffline();
		ArrayList<String> querys = fileManager.readQuerys();
		for (String query: querys) {
			System.out.println(query);
			String table = query.split("`")[1];
			if (table.toLowerCase().contains("usuario")) {
				Usuario user = new Usuario();
				String values = query.split("VALUES")[1];
				values = values.substring(values.indexOf("(") + 1);
				values = values.substring(0, values.indexOf(")"));
				String[] arrValues = values.split(",");
				
				user.setPassword(arrValues[0]);
				user.setEmail(arrValues[1]);
				user.setNombre(arrValues[2]);
				user.setNombre_usuario(arrValues[3]);
				user.setRol_usuario(userTypeEnum.values()[Integer.parseInt(arrValues[4])]);
				IUsuario userDao = new UsuarioDAOImpl();
				try {
					userDao.crearUsuario(user);
				} catch (Exception e) {
					fileManager.addQuery(query);
				}
			} else if (table.toLowerCase().contains("proyecto")) {
				Proyecto proyecto = new Proyecto();
				String values = query.split("VALUES")[1];
				values = values.substring(values.indexOf("(") + 1);
				values = values.substring(0, values.indexOf(")"));
				String[] arrValues = values.split(",");
				proyecto.setDescripcion(arrValues[0]);
				proyecto.setNombre_proyecto(arrValues[1]);
				
				IUsuario userDao = new UsuarioDAOImpl();
				proyecto.setProductOwner(userDao.getUsuarioById(Integer.parseInt(arrValues[2])));
				proyecto.setScrumMaster(userDao.getUsuarioById(Integer.parseInt(arrValues[3])));
				IProyecto proyectDao = new ProyectoDAOImpl();
				try {
					proyectDao.crearProyecto(proyecto);
				} catch (Exception e) {
					fileManager.addQuery(query);
				}
			} else if (table.toLowerCase().contains("especificacion")) {
				
			}
		}
	}
	
	
	/**
	 * Se conecta a la bbdd embebida y devuelve true o false dependiendo de si se ha podido conectar o no
	 * @return
	 */
	public boolean connectEmbbebedDB() {
		try {
			Connection con;
			con = DriverManager.getConnection("jdbc:sqlite:./data2.sqlite");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
