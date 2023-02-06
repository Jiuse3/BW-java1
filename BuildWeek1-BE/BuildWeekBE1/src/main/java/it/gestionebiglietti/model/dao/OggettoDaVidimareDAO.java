package it.gestionebiglietti.model.dao;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class OggettoDaVidimareDAO {
	
	private static final String aziendaTrasporti = "BuildWeekBE1";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(aziendaTrasporti);
	private static final EntityManager em = emf.createEntityManager();
	private static final EntityTransaction t = em.getTransaction();

	public static String generaCodice() {

		String codice = UUID.randomUUID().toString();
		return codice;

	}
	

	public static void stampaCodice(List<UUID> arrCodice) {
		arrCodice.get(arrCodice.size() - 1);
	}
	
}
