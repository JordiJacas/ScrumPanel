package daoImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import iDao.IUsuario;
import modelo.Usuario;

public class UsuarioDAOImpl implements IUsuario{

	public void crearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
		EntityManager entityManager = factory.createEntityManager();
		 
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		
		entityManager.close();
        factory.close();
	}

	public Usuario getUsuarioByNombreUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql = "SELECT u from Usuario u where u.nombre_usuario = '" + nombreUsuario + "'";
		Query query = entityManager.createQuery(sql);
		Usuario user = (Usuario) query.getSingleResult();
		
		return user;
	}

	public ArrayList<Usuario> getUsarioAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
