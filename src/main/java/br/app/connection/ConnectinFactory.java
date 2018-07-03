package br.app.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectinFactory {
	
	private static EntityManagerFactory emf;
	
	static{
		
		emf = Persistence.createEntityManagerFactory("projetomaratona");
	}
	
	
	public static EntityManager getConnection(){
		
		
		return emf.createEntityManager();
	}
	
	public static void closeConnection(){
		if(emf != null && emf.isOpen()){
			
			emf.close();
		}
		
	}
	
		
	

}
