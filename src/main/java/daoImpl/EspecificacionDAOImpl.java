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
	
	public List<Especificacion> getAllEspecifiacion(Proyecto proyecto) {
		// TODO Auto-generated method stub
		
		
		connect();
		
		entityManager.getTransaction().begin();
		entityManager.persist(new Especificacion("dsa",1,proyecto));
		entityManager.getTransaction().commit();
		
		close();
		
		return null;
	}

	public List<Especificacion> getAllEspecifiacionByProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT u from Proyecto_Especificacion u where u.Proyecto_proyecto_id = 1";
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
