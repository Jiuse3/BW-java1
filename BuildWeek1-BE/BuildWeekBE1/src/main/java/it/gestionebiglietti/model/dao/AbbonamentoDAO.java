package it.gestionebiglietti.model.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.gestionebiglietti.model.Abbonamento;
import it.gestionebiglietti.model.Tessera;
import it.gestionemezzi.model.dao.GestioneMezziDAO;

public class AbbonamentoDAO {
	private static final String aziendaTrasporti = "BuildWeekBE1";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(aziendaTrasporti);
	private static final EntityManager em = emf.createEntityManager();
	private static final EntityTransaction t = em.getTransaction();

	//SALVA ABBONAMENTO
	public static void salvaAbbonamento(String codice, Tessera tessera, LocalDate dataIni, LocalDate dataFine) {

		try {
			Abbonamento abbonamento = new Abbonamento();
			abbonamento.setCodUnivoco(codice);
			abbonamento.setTessera(tessera);
			abbonamento.setDataEmissione(dataIni);
			abbonamento.setDataScandenza(dataFine);

			t.begin();
			em.persist(abbonamento);
			t.commit();
		} catch (Exception e) {
			System.out.println("Errore nell'inserimento dell' abbonamento");
		}

	}
	
	public static void controlloCodiceAbbonamento(String codice, Tessera tessera, LocalDate dataIni, LocalDate dataFine) {

		Query q = em.createQuery("SELECT c.codUnivoco FROM OggettoDaVidimare c WHERE c.codUnivoco = :valore");
		q.setParameter("valore", codice);

		List<String> res = q.getResultList();
		
		if (res.isEmpty()) {
			salvaAbbonamento(codice, tessera, dataIni, dataFine);
			System.out.println(tessera.getUtente().getNome()+" "+tessera.getUtente().getCognome()+" il tuo"
					+ " codice abbonamento è "+codice+" SALVALO PER USI FUTURI");
		} else {
			System.out.println("ERRORE DEL SISTEMA");
		}

	}
	
	//CONTROLLA PRESENZA ABBONAMENTO NEL DB
	public static void controlloPossessoAbbonamento(Scanner scanner, long idBi){
		System.out.println("");
		System.out.println("Inserisci il codice del tuo abbonamento");
		System.out.println("");
		try {
			String codice=scanner.nextLine();
			checkUtenteAbbonamento(codice, scanner, idBi);
		}
		catch(NoResultException e) {
			System.out.println("Abbonamento non trovato");
			AziendaTrasportiDAO.funzionamento();
		}

		
	}
	
	public static void checkUtenteAbbonamento(String codice, Scanner scanner, long idBi) {

		Query q = em.createQuery("SELECT a.codUnivoco FROM Abbonamento a WHERE a.codUnivoco = :valore");
		q.setParameter("valore", codice);
		
		Query q2 = em.createQuery("SELECT a FROM Abbonamento a WHERE a.codUnivoco = :valore");
        q2.setParameter("valore", codice);

		List<String> res = q.getResultList();
        
        Abbonamento a = (Abbonamento) q2.getSingleResult();

		if (res.isEmpty()) {
			System.out.println("");
			System.out.println("Non hai un abbonamento, acquistalo!");
			System.out.println("");
			AziendaTrasportiDAO.funzionamento();
		} else {
			if(a.getDataScandenza().isBefore(LocalDate.now())) {
                System.out.println();
                System.out.println("Abbonamento " + a.getCodUnivoco() + " è scaduto il " + a.getDataScandenza() + "\nRinnovalo oppure acquistane un altro.");
                System.out.println("");
                rinnovaOcompra(scanner, idBi, a);
            }else {
                GestioneMezziDAO.selectTratta(codice);
            }
		}

	}
	
	public static void checkValiditaAbbonamento(String codice, Scanner scanner, long idBi) {
		Query q2 = em.createQuery("SELECT a FROM Abbonamento a WHERE a.codUnivoco = :valore");
        q2.setParameter("valore", codice);
        
        Abbonamento a = (Abbonamento) q2.getSingleResult();
        
		if(a.getDataScandenza().isBefore(LocalDate.now())) {
            System.out.println();
            System.out.println("Abbonamento " + a.getCodUnivoco() + " è scaduto il "
            		+ "" + a.getDataScandenza() + "\nRinnovalo oppure acquistane un altro.");
            System.out.println("");
            rinnovaOcompra(scanner, idBi, a);
            
        }else {
        	System.out.println("");
            System.out.println("Abbonamento "+ a.getCodUnivoco() +" è ancora valido e "
            		+ "scadrà il "+ a.getDataScandenza());
            System.out.println("");
            AziendaTrasportiDAO.funzionamento();
        }
	}
	
	public static void rinnovaOcompra(Scanner scanner, long idBi, Abbonamento ab) {
		System.out.println("DIGITA 1 PER RINNOVARE L'ABBONAMENTO\n"
				+ "DIGITA 2 PER COMPRARNE UNO NUOVO\n"
				+ "DIGITA 3 PER TORNARE ALL'INIZIO");
		try {
			int scelta=Integer.valueOf(scanner.nextLine());
			if(scelta==1) {
				rinnovoAbbonamento(ab, scanner);
			}
			else if(scelta==2) {
				AziendaTrasportiDAO.acquistoAbbonamento(scanner, idBi);
			}
			else if(scelta==3) {
				AziendaTrasportiDAO.funzionamento();
			}
			else {
				System.out.println("Errore nella digitazione");
				AziendaTrasportiDAO.funzionamento();
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Errore nella digitazione");
			AziendaTrasportiDAO.funzionamento();
		}
		
	}
	
	public static void rinnovoAbbonamento(Abbonamento ab, Scanner scanner){
		
		System.out.println("");
		System.out.println("DIGITA 1 PER RINNOVARE DI UNA SETTIMANA\n"
				+ "DIGITA 2 PER RINNOVARE DI UN MESE");
		try {
			int scelta=Integer.valueOf(scanner.nextLine());
			if(scelta==1) {
				t.begin();
				ab.setDataEmissione(LocalDate.now());
				ab.setDataScandenza(LocalDate.now().plusWeeks(1));
				t.commit();
				System.out.println("");
				System.out.println("Abbonamento rinnovato con successo");
				System.out.println("");
				AziendaTrasportiDAO.funzionamento();
			}
			else if(scelta==2) {
				t.begin();
				ab.setDataEmissione(LocalDate.now());
				ab.setDataScandenza(LocalDate.now().plusMonths(1));
				t.commit();
				System.out.println("");
				System.out.println("Abbonamento rinnovato con successo");
				System.out.println("");
				AziendaTrasportiDAO.funzionamento();
			}
			else {
				System.out.println("Errore nella digitazione");
				AziendaTrasportiDAO.funzionamento();
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Errore nella digitazione");
			AziendaTrasportiDAO.funzionamento();
		}

	}
	
}
