package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("curso-jsp");
	
	public static EntityManager getEntityManager(){
		return factory.createEntityManager();
	}

}
