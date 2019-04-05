package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import iDao.IProyecto;
import iDao.IUsuario;
import modelo.Especificacion;
import modelo.Proyecto;
import modelo.Usuario;

/**
* Esta clase contiene los metodos para acceder a la base de datos mysql y ejecutar la sentecia deseada para los proyectos
* @author: Jordi Jacas
* @version: 1
*/
public class ProyectoDAOImpl implements IProyecto{
	
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	
    /**
     * Metodo para buscar el proyecto por el nombre
     * @param nombre_proyecto 
     * @return El proyecto buscado
     */
	
	public Proyecto getProyectoByName(String nombre_proyecto) {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT p from Proyecto p where p.nombre_proyecto = '" + nombre_proyecto + "'";
		Query query = entityManager.createQuery(sql);
		Proyecto proyecto = (Proyecto) query.getSingleResult();
		
		close();
		return proyecto;
	}//Cierre del metodo
	
    /**
     * Metodo insertar un proyecto a la base de datps
     * @param proyecto 
     */
	
	public void crearProyecto(Proyecto proyecto) throws Exception {
		connect();
		try {
		entityManager.getTransaction().begin();
		entityManager.persist(proyecto);
		entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
		
		close();
		
		IUsuario gUsuario = new UsuarioDAOImpl();
		gUsuario.updateUsuario(proyecto.getProductOwner(), proyecto);
		
		IUsuario gUsuario2 = new UsuarioDAOImpl();
		gUsuario2.updateUsuario(proyecto.getScrumMaster(), proyecto);
	}//Cierre del metodo
	
    /**
     * Metodo para obtener todos los proyectos
     * @return Lista de proyectos
     */
	
	public List<Proyecto> getAllProyectos() {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT p from Proyecto p";
		Query query = entityManager.createQuery(sql);
		List<Proyecto> proyectos = query.getResultList();
		
		close();
		return proyectos;
	}//Cierre del metodo
	
    /**
     * Metodo para buscar todos los proyectos de un usuario
     * @param usuario
     * @return Lista de proyectos 
     */

	public List<Proyecto> getProyectosByUser(Usuario usuario) {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT u from Usuario u where u.nombre_usuario = '" + usuario.getNombre_usuario() + "'";
		Query query = entityManager.createQuery(sql);
		Usuario user = (Usuario) query.getSingleResult();
		
		close();
		return user.getGrupo_proyecto_id();
	}//Cierre del metodo
	
    /**
     * Metodo para connectar a la base de datos
     */
	
	private void connect() {
		factory = Persistence.createEntityManagerFactory("ScrumDB");
		entityManager = factory.createEntityManager();
	}//Cierre del metodo
	
    /**
     * Metodo para cerrar la connecion de la base de datos
     */
	private void close() {
		entityManager.close();
        factory.close();
	}//Cierre del metodo

}//Cierre de la classe
