package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import enumClass.userTypeEnum;
import iDao.IUsuario;
import modelo.Usuario;

public class UsuarioDAOImpl implements IUsuario{
	
	EntityManagerFactory factory;
	EntityManager entityManager;

	public void crearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		connect();
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		
		close();
	}

	public Usuario getUsuarioByNombreUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub		
		
		connect();
		
		String sql = "SELECT u from Usuario u where u.nombre_usuario = '" + nombreUsuario + "'";
		Query query = entityManager.createQuery(sql);
		Usuario user = (Usuario) query.getSingleResult();
		
		close();
		
		return user;
	}

	public List<Usuario> getUsuariosByRol(userTypeEnum rol) {
		// TODO Auto-generated method stub
		connect();
		
		String sql = "SELECT u from Usuario u where u.rol_usuario = '" + rol + "'";
		Query query = entityManager.createQuery(sql);
		List<Usuario> users = query.getResultList();
		
		close();
        
		return users;
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
