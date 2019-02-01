package daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import iDao.IProyecto;
import modelo.Proyecto;
import modelo.Usuario;

public class ProyectoDAOImpl implements IProyecto{
	
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	
	public Proyecto getProyectoByName(String nombre_proyecto) {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT p from Proyecto p where p.nombre_proyecto = '" + nombre_proyecto + "'";
		Query query = entityManager.createQuery(sql);
		Proyecto proyecto = (Proyecto) query.getSingleResult();
		
		System.out.println(proyecto.toString());
		close();
		return proyecto;
	}
	
	public void crearProyecto(Proyecto proyecto) {
		connect();
		
		entityManager.getTransaction().begin();
		entityManager.persist(proyecto);
		entityManager.getTransaction().commit();
		
		close();
	}
	
	private void connect() {
		factory = Persistence.createEntityManagerFactory("ScrumDB");
		entityManager = factory.createEntityManager();
	}
	
	private void close() {
		entityManager.close();
        factory.close();
	}

	public List<Proyecto> getAllProyectos() {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT p from Proyecto p";
		Query query = entityManager.createQuery(sql);
		List<Proyecto> proyectos = query.getResultList();
		
		close();
		return proyectos;
	}

}
