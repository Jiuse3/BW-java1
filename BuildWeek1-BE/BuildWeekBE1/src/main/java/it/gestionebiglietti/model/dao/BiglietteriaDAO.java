package it.gestionebiglietti.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.gestionebiglietti.model.Biglietteria;
import it.gestionebiglietti.model.Tessera;
import it.gestionebiglietti.model.Utente;

public class BiglietteriaDAO {

	private static final String aziendaTrasporti = "BuildWeekBE1";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(aziendaTrasporti);
	private static final EntityManager em = emf.createEntityManager();
	private static final EntityTransaction t = em.getTransaction();

	
	//INCREMENTO BIGLIETTI VENDUTI
	public static void incrementoBiglietti(long valore) {

		t.begin();
		int ab=selezionaBiglietteriaDaId(valore).getBigliettiEmessi();
		ab=ab+1;
		selezionaBiglietteriaDaId(valore).setBigliettiEmessi(ab);
		t.commit();
	}
	
	public static Biglietteria selezionaBiglietteriaDaId(long valore) {
		Query q = em.createQuery("SELECT b FROM Biglietteria b WHERE b.id = :valore");
		q.setParameter("valore", valore);
		return (Biglietteria) q.getSingleResult();
	}
	
	//INCREMENTO ABBONAMENTI VENDUTI
	public static void incrementoAbbonamenti(long valore) {

		t.begin();
		int ab=selezionaBiglietteriaDaId(valore).getAbbonamentiEmessi();
		ab=ab+1;
		selezionaBiglietteriaDaId(valore).setAbbonamentiEmessi(ab);
		t.commit();
	}

}
