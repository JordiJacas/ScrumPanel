package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import enumClass.userTypeEnum;
import modelo.Usuario;


public class ConnnectDBDaoRemote {
	
	private static String state = "ONLINE";

	public String getState() {
		return this.state;
	}
	
	public ConnnectDBDaoRemote() {
		// TODO Auto-generated method stub
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
			EntityManager entityManager = factory.createEntityManager();
			entityManager.close();
			factory.close();
		} catch (Exception e) {
			state = "OFFLINE";
		}		
	}
}
