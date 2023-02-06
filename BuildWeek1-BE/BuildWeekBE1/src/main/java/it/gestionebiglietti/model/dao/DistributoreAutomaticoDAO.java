package it.gestionebiglietti.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.gestionebiglietti.model.Biglietteria;
import it.gestionebiglietti.model.DistributoreAutomatico;

public class DistributoreAutomaticoDAO {
	
	private static final String aziendaTrasporti = "BuildWeekBE1";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(aziendaTrasporti);
	private static final EntityManager em = emf.createEntityManager();
	private static final EntityTransaction t = em.getTransaction();

	public static void istanziaDistributori() {
		
		DistributoreAutomatico da1=new DistributoreAutomatico(true);
		DistributoreAutomatico da2=new DistributoreAutomatico(true);
		DistributoreAutomatico da3=new DistributoreAutomatico(false);
		salvaDistributoreAutomatico(da1);
		salvaDistributoreAutomatico(da2);
		salvaDistributoreAutomatico(da3);
		
	}
	
	public static void salvaDistributoreAutomatico(DistributoreAutomatico object) {

		try {
			DistributoreAutomatico da = object;

			t.begin();
			em.persist(da);
			t.commit();

		} catch (Exception e) {
			System.out.println("Errore nell'inserimento del distributore");
		}

	}
	
	//VERIFICA SE IL DISTRIBUTORE è ATTIVO O MENO
	public static void checkDistributore(long idBi, Scanner scanner) {

		if (selezionaDistibutoreDaId(idBi).isInServizio()) {
			AziendaTrasportiDAO.distributore(idBi, scanner);
		} else {
			System.out.println("Il distributore non è attivo");
			AziendaTrasportiDAO.funzionamento();
		}
		
	}
	
	public static DistributoreAutomatico selezionaDistibutoreDaId(long valore) {
		Query q = em.createQuery("SELECT d FROM DistributoreAutomatico d WHERE d.id = :valore");
		q.setParameter("valore", valore);
		return (DistributoreAutomatico) q.getSingleResult();
	}
	
}
