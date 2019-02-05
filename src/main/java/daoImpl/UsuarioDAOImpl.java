package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import enumClass.userTypeEnum;
import iDao.IUsuario;
import modelo.Proyecto;
import modelo.Usuario;

public class UsuarioDAOImpl implements IUsuario{
	
	EntityManagerFactory factory;
	EntityManager entityManager;

	public void crearUsuario(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		
		connect();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(usuario);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
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

	public Usuario getUsuarioById(int id) {
		Usuario user;
		connect();
		
		String sql = "SELECT u from Usuario u where u.usuario_id = " + id + "";
		Query query = entityManager.createQuery(sql);
		user = (Usuario) query.getSingleResult();
		
		close();
		return user;
	}
	
	public List<Usuario> getUsuariosByRol(userTypeEnum rol) {
		// TODO Auto-generated method stub

		connect();
		
		String sql = "SELECT u from Usuario u where u.rol_usuario = '" + rol.ordinal() + "'";
		Query query = entityManager.createQuery(sql);
		List<Usuario> users = query.getResultList();
		
		close();
        
		return users;
	}
	
	public void updateUsuario(Usuario usuario, Proyecto proyecto) {
		// TODO Auto-generated method stub
		connect();
		
		entityManager.getTransaction().begin();
		//Usuario user = entityManager.find(Usuario.class, usuario.getUsuario_id());
		List<Proyecto> proyectos = usuario.getGrupo_proyecto_id();
		proyectos.add(proyecto);
		usuario.setGrupo_proyecto_id(proyectos);
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

}
