package daoTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConnnectDBDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
		EntityManager entityManager = factory.createEntityManager();
		 
		entityManager.getTransaction().begin();
		
        entityManager.getTransaction().begin();
        
        /*test newUser = new test();
        newUser.setName("testName");
         
        entityManager.persist(newUser);
         
        entityManager.getTransaction().commit();*/
         
        entityManager.close();
        factory.close();
	}

}
