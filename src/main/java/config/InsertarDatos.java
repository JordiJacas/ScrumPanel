package config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import enumClass.userTypeEnum;
import modelo.Especificacion;
import modelo.Proyecto;
import modelo.Usuario;


/**
 * Clase que insertara datos basicos con los que trabajara la bbdd
 * @author jaimepm
 *
 */
public class InsertarDatos {
	
	/**
	 * Constructor que se encargara de insertar los datos
	 */
	public InsertarDatos() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
		EntityManager entityManager = factory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		List<Proyecto> proyectos = new ArrayList<Proyecto>();
		//proyectos.add(new Proyecto("Proyecto 1","Proyecto 1", new Usuario(5), new Usuario(4),null));
		//proyectos.add(new Proyecto("Proyecto 2","Proyecto 2", new Usuario(5), new Usuario(4),null));
		
		List<Proyecto> proyectos2 = new ArrayList<Proyecto>();
		//proyectos2.add(new Proyecto("Proyecto 3","Proyecto 3", new Usuario(5), new Usuario(4),null));
		//proyectos2.add(new Proyecto("Proyecto 4","Proyecto 4", new Usuario(5), new Usuario(4),null));
		
		List<Proyecto> proyectos3 = new ArrayList<Proyecto>();
		//proyectos3.add(new Proyecto("Proyecto 1","Proyecto 1", new Usuario(5), new Usuario(4),null));
		//proyectos3.add(new Proyecto("Proyecto 2","Proyecto 2", new Usuario(5), new Usuario(4),null));
		//proyectos3.add(new Proyecto("Proyecto 3","Proyecto 3", new Usuario(5), new Usuario(4),null));
		//proyectos3.add(new Proyecto("Proyecto 4","Proyecto 4", new Usuario(5), new Usuario(4),null));
		
		Proyecto proyecto = new Proyecto("Proyecto 1","Proyecto 1", new Usuario(5), new Usuario(4),null);
		Proyecto proyecto2 = new Proyecto("Proyecto 2","Proyecto 2", new Usuario(5), new Usuario(4),null);
		Proyecto proyecto3 = new Proyecto("Proyecto 3","Proyecto 3", new Usuario(5), new Usuario(4),null);
		Proyecto proyecto4 = new Proyecto("Proyecto 4","Proyecto 4", new Usuario(5), new Usuario(4),null);
		
		proyectos.add(proyecto);
		proyectos.add(proyecto2);
		proyectos2.add(proyecto3);
		proyectos2.add(proyecto4);
		proyectos3.add(new Proyecto(1));
		proyectos3.add(new Proyecto(2));
		proyectos3.add(new Proyecto(3));
		proyectos3.add(new Proyecto(4));
		
		Usuario newUser = new Usuario();
		newUser.setNombre("Administrador Usuario");
		newUser.setNombre_usuario("uadmin");
		newUser.setPassword("123");
		newUser.setEmail("admin.usuario@gmail.com");
		newUser.setRol_usuario(userTypeEnum.USER_ADMINISTRATOR);
		
		Usuario newUser2 = new Usuario();
		newUser2.setNombre("User Developer 1");
		newUser2.setNombre_usuario("udev");
		newUser2.setPassword("123");
		newUser2.setEmail("user.1@gmail.com");
		newUser2.setRol_usuario(userTypeEnum.DEVELOPER);
		newUser2.setGrupo_proyecto_id(proyectos);
		
		Usuario newUser3 = new Usuario();
		newUser3.setNombre("User Developer 2");
		newUser3.setNombre_usuario("udev2");
		newUser3.setPassword("123");
		newUser3.setEmail("user.2@gmail.com");
		newUser3.setRol_usuario(userTypeEnum.DEVELOPER);
		newUser3.setGrupo_proyecto_id(proyectos2);
		
		Usuario newUser4 = new Usuario();
		newUser4.setNombre("User Owner Product");
		newUser4.setNombre_usuario("uowner");
		newUser4.setPassword("123");
		newUser4.setEmail("owner@gmail.com");
		newUser4.setRol_usuario(userTypeEnum.PRODUCT_OWNER);
		newUser4.setGrupo_proyecto_id(proyectos3);
		
		Usuario newUser5 = new Usuario();
		newUser5.setNombre("User Scrum Master");
		newUser5.setNombre_usuario("uscrum");
		newUser5.setPassword("123");
		newUser5.setEmail("scrum@gmail.com");
		newUser5.setRol_usuario(userTypeEnum.SCRUM_MASTER);
		newUser5.setGrupo_proyecto_id(proyectos3);
		
		Especificacion newTarea = new Especificacion("Tarea1", new Proyecto(1));
		Especificacion newTarea2 = new Especificacion("Tarea2", new Proyecto(1));
		Especificacion newTarea3 = new Especificacion("Tarea1", new Proyecto(2));
		Especificacion newTarea4 = new Especificacion("Tarea1", new Proyecto(3));
		Especificacion newTarea5 = new Especificacion("Tarea1", new Proyecto(4));
		Especificacion newTarea6 = new Especificacion("Tarea2", new Proyecto(4));
		Especificacion newTarea7 = new Especificacion("Tarea3", new Proyecto(4));
		
		entityManager.persist(newUser);
		entityManager.persist(newUser2);
		entityManager.persist(newUser3);
		entityManager.persist(newUser4);
		entityManager.persist(newUser5);
		
		entityManager.persist(proyecto);
		entityManager.persist(proyecto2);
		entityManager.persist(proyecto3);
		entityManager.persist(proyecto4);
		
		entityManager.persist(newTarea);
		entityManager.persist(newTarea2);
		entityManager.persist(newTarea3);
		entityManager.persist(newTarea4);
		entityManager.persist(newTarea5);
		entityManager.persist(newTarea6);
		entityManager.persist(newTarea7);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
	    factory.close();
	  
	}
	
	/**
	 * Llama al constructor de la clase
	 * @param args
	 */
	public static void main(String[] args) {
		InsertarDatos insertar = new InsertarDatos();
	}
	
	
	
}
