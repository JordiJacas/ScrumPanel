package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import enumClass.userTypeEnum;
import iDao.IUsuario;
import modelo.Proyecto;
import modelo.Usuario;

/**
* Esta clase contiene los metodos para acceder a la base de datos mysql y ejecutar la sentecia deseada para los usuarios
* @author: Jordi Jacas
* @version: 1
*/

public class UsuarioDAOImpl implements IUsuario{
	
	EntityManagerFactory factory;
	EntityManager entityManager;
	
	/**
	* Metodo para insertar un usuario a la base de datos
	* @param usuario
	*/
	public void crearUsuario(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		
		connect();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(usuario);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
		close();
	}//Cierre del metodo
	
	/**
	* Metodo buscar un usuario por nombre
	* @param nombreUsuario
	* @return usuario
	*/

	public Usuario getUsuarioByNombreUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub		
		
		connect();
		
		String sql = "SELECT u from Usuario u where u.nombre_usuario = '" + nombreUsuario + "'";
		Query query = entityManager.createQuery(sql);
		Usuario user = (Usuario) query.getSingleResult();
		
		close();
		
		return user;
	}//Cierre del metodo
	
	/**
	* Metodo buscar un usuario por id
	* @param id
	* @return usuario
	*/

	public Usuario getUsuarioById(int id) {
		Usuario user;
		connect();
		
		String sql = "SELECT u from Usuario u where u.usuario_id = " + id + "";
		Query query = entityManager.createQuery(sql);
		user = (Usuario) query.getSingleResult();
		
		close();
		return user;
	}//Cierre del metodo
	
	/**
	* Metodo buscar todos los usuarios que sean de un tipo en concreto
	* @param rol
	* @return Lista de usuarios
	*/
	
	public List<Usuario> getUsuariosByRol(userTypeEnum rol) {
		// TODO Auto-generated method stub

		connect();
		
		String sql = "SELECT u from Usuario u where u.rol_usuario = '" + rol.ordinal() + "'";
		Query query = entityManager.createQuery(sql);
		List<Usuario> users = query.getResultList();
		
		close();
        
		return users;
	}//Cierre del metodo
	
	/**
	* Metodo para actualizar un usuario
	* @param usuario
	* param proyecto
	*/
	
	public void updateUsuario(Usuario usuario, Proyecto proyecto) {
		// TODO Auto-generated method stub
		List<Proyecto> proyectos = new ArrayList<Proyecto>();
		connect();
		
		entityManager.getTransaction().begin();
		proyectos = usuario.getGrupo_proyecto_id();
		proyectos.add(proyecto);
		usuario.setGrupo_proyecto_id(proyectos);
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
		
		close();
		
		
	}//Cierre del metodo
	
	/**
	* Metodo para conectar a la base de datos
	*/
	
	private void connect() {
		factory = Persistence.createEntityManagerFactory("ScrumDB");
		entityManager = factory.createEntityManager();
	}//Cierre del metodo
	
	/**
	* Metodo cerrar la coneccion a la base de datos
	*/
	
	private void close() {
		entityManager.close();
        factory.close();
	}//Cierre del metodo

}//Cierre de la classe
