package config;

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


public class ConnnectDBDaoRemote {
	
	//private static String state = "ONLINE";
	private static boolean state = true;
	
	public boolean getState() {
		return this.state;
	}
	
	public String getStateString() {
		
		if(this.state) return "ONLINE";
		if(!this.state) return "OFFLINE";
		
		return null;
	}
	
	public ConnnectDBDaoRemote() {
		// TODO Auto-generated method stub
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumDB");
			EntityManager entityManager = factory.createEntityManager();
			entityManager.close();
			factory.close();

			//Connection con = DriverManager.getConnection("jdbc:sqlite:./testdb");
			System.out.println("test");
		} catch (Exception e) {
			System.out.println(e);
			state = false;
		}
		
	}
}
