package it.gestionebiglietti.model.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.gestionebiglietti.model.Tessera;
import it.gestionebiglietti.model.Utente;

public class TesseraDAO {

	private static final String aziendaTrasporti = "BuildWeekBE1";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(aziendaTrasporti);
	private static final EntityManager em = emf.createEntityManager();
	private static final EntityTransaction t = em.getTransaction();

	public static void salvaTessera(int numTessera, Utente utente) {
		Tessera tessera = new Tessera();
		tessera.setNumTessera(numTessera);
		tessera.setUtente(utente);
		tessera.setDataInizio(LocalDate.now());
		tessera.setDataFine(tessera.getDataInizio().plusYears(1));

		try {
			t.begin();
			em.persist(tessera);
			t.commit();
			System.out.println("Tessera generata correttamente");
			System.out.println("Il tuo codice tessera "+utente.getNome()+" "+utente.getCognome()+" è "+
			numTessera+" (SALVALO) e scade "+tessera.getDataFine());
		} catch (Exception e) {
			System.out.println("Errore nell'inserimento della tessera");
		}
	}


	public static void checkUtenteTessera(int numTessera, Scanner scanner, long idBi) {

		Query q = em.createQuery("SELECT t.numTessera FROM Tessera t WHERE t.numTessera = :valore");
		q.setParameter("valore", numTessera);

		List<Integer> res = q.getResultList();

		if (res.isEmpty()) {
			System.out.println("");
			System.out.println("Non hai una tessera associata, acquistane una");
			System.out.println("");
			AziendaTrasportiDAO.funzionamento();
		} else {
			AziendaTrasportiDAO.abbonamentoSceltaTemporale(recuperaTessera(numTessera), scanner, idBi);
		}

	}

	// CHIAMA LA GENERAZIONE DI UN NUMERO TESSERA E CONTROLLA SE GIà ESISTE
	public static void checkNumeroTessera(int numTessera, Utente utente) {

		Query q = em.createQuery("SELECT t.numTessera FROM Tessera t WHERE t.numTessera = :valore");
		q.setParameter("valore", numTessera);

		List<Integer> res = q.getResultList();

		if (res.isEmpty()) {
			salvaTessera(numTessera, utente);
		} else {
			System.out.println("ERRORE DI SISTEMA");
		}

	}
	
	//RECUPERA ID TESSERA UTENTE
	public static Tessera recuperaTessera(int numTessera) {
		
		Query q = em.createQuery("SELECT t FROM Tessera t WHERE t.numTessera = :valore");
		q.setParameter("valore", numTessera);
		return (Tessera) q.getSingleResult();
		
	}

	public static int generaNumTessera() {
		return (int) Math.floor(Math.random() * 1000000 + 1);
	}

}
