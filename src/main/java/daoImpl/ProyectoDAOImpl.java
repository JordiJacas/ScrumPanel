package daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import iDao.IProyecto;
import modelo.Proyecto;
import modelo.Usuario;

public class ProyectoDAOImpl implements IProyecto{
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
	EntityManager entityManager = factory.createEntityManager();
	
	public Proyecto getProyectoByName(String nombre_proyecto) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT p from Proyecto p where p.nombre_proyecto = '" + nombre_proyecto + "'";
		Query query = entityManager.createQuery(sql);
		Proyecto proyecto = (Proyecto) query.getSingleResult();
		
		entityManager.close();
        factory.close();
        
		return proyecto;
	}

}
