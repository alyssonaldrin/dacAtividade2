package br.com.dacAtividade2.model.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("dacAtividade2");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
