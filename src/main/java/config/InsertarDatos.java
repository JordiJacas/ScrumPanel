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
		newUser.setContraseña("P@ssw0rd");
		newUser.setEmail("admin.usuario@gmail.com");
		newUser.setRol_usuario(userTypeEnum.USER_ADMINISTRATOR);
		
		entityManager.persist(newUser);
		entityManager.getTransaction().commit();
		
		entityManager.close();
	    factory.close();
	}
	 
	
	
	
}
