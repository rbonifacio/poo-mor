package br.unb.cic.poo.imdb.data.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SQLite implements JPAManager {
	
	private EntityManagerFactory emf;
	
	private static SQLite instance;
	
	private SQLite() {
		emf = Persistence.createEntityManagerFactory("sample");
	} 
	
	public static SQLite instance() {
		if(instance == null) {
			instance = new SQLite();
		}
		return instance;
	}
	@Override
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
