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
	
	public List<Especificacion> getAllEspecifiacion() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Especificacion> getAllEspecifiacionByProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT e from especificacion e where e.proyecto_id = '" + proyecto + "'";
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
