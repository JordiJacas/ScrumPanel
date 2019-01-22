package daoTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import enumClass.userTypeEnum;
import modelo.Usuario;


public class ConnnectDBDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
		EntityManager entityManager = factory.createEntityManager();
		 
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
		
		entityManager.close();
        factory.close();
	}
}
