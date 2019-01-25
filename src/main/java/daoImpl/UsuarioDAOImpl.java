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
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
	EntityManager entityManager = factory.createEntityManager();

	public void crearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		
		entityManager.close();
        factory.close();
	}

	public Usuario getUsuarioByNombreUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub		
		String sql = "SELECT u from Usuario u where u.nombre_usuario = '" + nombreUsuario + "'";
		Query query = entityManager.createQuery(sql);
		Usuario user = (Usuario) query.getSingleResult();
		
		entityManager.close();
        factory.close();
		
		return user;
	}

	public List<Usuario> getUsuariosByRol(userTypeEnum rol) {
		// TODO Auto-generated method stub
		String sql = "SELECT u from Usuario u where u.rol_usuario = '" + rol.ordinal() + "'";
		Query query = entityManager.createQuery(sql);
		List<Usuario> users = query.getResultList();
		
		entityManager.close();
        factory.close();
        
		return users;
	}

}
