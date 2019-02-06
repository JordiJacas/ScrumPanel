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

public class EspecificacionDAOImpl implements iEspecificacion{
	
	EntityManagerFactory factory;
	EntityManager entityManager;
	
	public void createEspecificacion(Especificacion especificacion) {
		// TODO Auto-generated method stub
		connect();
		
		entityManager.getTransaction().begin();
		entityManager.persist(especificacion);
		entityManager.getTransaction().commit();
		
		close();
	}

	public List<Especificacion> getAllEspecifiacionByProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT u from Especificacion u where u.proyecto_id = "+proyecto.getProyecto_id()+"";
		Query query = entityManager.createQuery(sql);
		List<Especificacion> especificaciones = query.getResultList();
		
		close();
        
		return especificaciones;
	}
	
	private void connect() {
		factory = Persistence.createEntityManagerFactory("ScrumDB");
		entityManager = factory.createEntityManager();
	}
	
	private void close() {
		entityManager.close();
        factory.close();
	}
}
