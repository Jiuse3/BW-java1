package it.gestionebiglietti.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.gestionebiglietti.model.RivenditoreFisico;

public class RivenditoreFisicoDAO {
	
	private static final String aziendaTrasporti = "BuildWeekBE1";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(aziendaTrasporti);
	private static final EntityManager em = emf.createEntityManager();
	private static final EntityTransaction t = em.getTransaction();
	
	public static void istanziaRivenditoreFisico(){
		RivenditoreFisico rf=new RivenditoreFisico();
		salvaRivenditoreFisico(rf);

	}


	public static void salvaRivenditoreFisico(RivenditoreFisico object) {

		try {
			RivenditoreFisico rf = object;

			t.begin();
			em.persist(rf);
			t.commit();

		} catch (Exception e) {
			System.out.println("Errore nell'inserimento del rivenditore");
		}

	}
	
}
