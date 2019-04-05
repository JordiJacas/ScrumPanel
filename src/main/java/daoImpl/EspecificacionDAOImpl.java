package daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import iDao.iEspecificacion;
import modelo.Especificacion;
import modelo.Proyecto;
import modelo.Usuario;

/**
* Esta clase contiene los metodos para acceder a la base de datos mysql y ejecutar la sentecia deseada para las especificaciones
* @author: Jordi Jacas
* @version: 1
*/
public class EspecificacionDAOImpl implements iEspecificacion{
	
	EntityManagerFactory factory;
	EntityManager entityManager;
	
    /**
     * Metodo para insertar una especificacion en la base de datos
     * @param especificacion 
     */
	
	public void createEspecificacion(Especificacion especificacion) {
		// TODO Auto-generated method stub
		connect();
		
		System.out.println(especificacion.toString());
		
		entityManager.getTransaction().begin();
		entityManager.persist(especificacion);
		entityManager.getTransaction().commit();
		
		close();
	}//Cierre de metodo
	
    /**
     * Metodo que busca en la base de datos todas las especificaciones de un proyecto esecifico
     * @param proyecto 
     * @return Una lista de todas las especificaciones
     */

	public List<Especificacion> getAllEspecifiacionByProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT u from Especificacion u where u.proyecto_id = "+proyecto.getProyecto_id()+"";
		Query query = entityManager.createQuery(sql);
		List<Especificacion> especificaciones = query.getResultList();
		
		close();
        
		return especificaciones;
	}//Cierre del metodo
	
	/**
     * Metodo que conecta a la base de datos
     */
	
	private void connect() {
		factory = Persistence.createEntityManagerFactory("ScrumDB");
		entityManager = factory.createEntityManager();
	}//Cierre del metodo
	
	/**
     * Metodo que cierra la coneccion de la base de datos
     */
	
	
	private void close() {
		entityManager.close();
        factory.close();
	}
}
