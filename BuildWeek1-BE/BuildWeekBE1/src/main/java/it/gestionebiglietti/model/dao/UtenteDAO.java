package it.gestionebiglietti.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.gestionebiglietti.model.Utente;

public class UtenteDAO {
	
	private static final String aziendaTrasporti = "BuildWeekBE1";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(aziendaTrasporti);
	private static final EntityManager em = emf.createEntityManager();
	private static final EntityTransaction t = em.getTransaction();

	public static Utente salvaUtente(String nome, String cognome) {
		Utente utente=new Utente(nome, cognome);
		try {
			t.begin();
			em.persist(utente);
			t.commit();
		}
		catch(Exception e) {
			System.out.println("Errore nell'inserimento dell' utente");
		}
		return utente;
	}	
}
