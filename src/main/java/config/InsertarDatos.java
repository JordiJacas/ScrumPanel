package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import enumClass.userTypeEnum;
import modelo.Usuario;

public class InsertarDatos {
	
	public InsertarDatos() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
		EntityManager entityManager = factory.createEntityManager();
		 
		entityManager.getTransaction().begin();
		
		Usuario newUser = new Usuario();
		newUser.setNombre("Administrador Usuario");
		newUser.setNombre_usuario("admin.usuario");
		newUser.setPassword("P@ssw0rd");
		newUser.setEmail("admin.usuario@gmail.com");
		newUser.setRol_usuario(userTypeEnum.USER_ADMINISTRATOR);
		
		Usuario newUser2 = new Usuario();
		newUser2.setNombre("User 1");
		newUser2.setNombre_usuario("user.1");
		newUser2.setPassword("P@ssw0rd");
		newUser2.setEmail("user.1@gmail.com");
		newUser2.setRol_usuario(userTypeEnum.DEVELOPER);
		
		entityManager.persist(newUser);
		entityManager.persist(newUser2);
		entityManager.getTransaction().commit();
		
		entityManager.close();
	    factory.close();
	}
	 
	
	
	
}
