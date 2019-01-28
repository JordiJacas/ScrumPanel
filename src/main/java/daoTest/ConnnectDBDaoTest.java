package daoTest;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import enumClass.userTypeEnum;
import modelo.Usuario;


public class ConnnectDBDaoTest {
	
	private static String state = "ONLINE";

	public String getState() {
		return this.state;
	}
	
	public ConnnectDBDaoTest() {
		// TODO Auto-generated method stub
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
			EntityManager entityManager = factory.createEntityManager();
//			entityManager.close();
//			factory.close();
			EntityManagerFactory factory2 = Persistence.createEntityManagerFactory("ScrumDBEmbebed");
			EntityManager entityManager2 = factory2.createEntityManager();

//			Connection con = DriverManager.getConnection("jdbc:sqlite:./testdb");
			System.out.println("test");
		} catch (Exception e) {
			System.out.println(e);
			state = "OFFLINE";
		}
		
		 
		//entityManager.getTransaction().begin();
				
		/*Usuario newUser = new Usuario();
		newUser.setNombre("Jordi Jacas");
		newUser.setNombre_usuario("jordi.jacas");
		newUser.setContraseña("P@ssw0rd");
		newUser.setEmail("jordi.jacas@gmail.com");
		newUser.setRol_usuario(userTypeEnum.USER_ADMINISTRATOR);
		
		entityManager.persist(newUser);
		entityManager.getTransaction().commit();*/		
		
		/*String sql = "SELECT u from Usuario u where u.nombre_usuario = 'jordi.jacas'";
		Query query = entityManager.createQuery(sql);
		Usuario user = (Usuario) query.getSingleResult();*/
		 
		/*System.out.println(user.getNombre_usuario());
		System.out.println(user.getContraseña());
		System.out.println(user.getNombre());*/
		
		//Integer primaryKey = 1;
		//Usuario user = entityManager.find(Usuario.class, primaryKey);
		 
		//System.out.println(user.getNombre());
		
		
	}
}
