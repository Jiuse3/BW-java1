package it.gestionebiglietti.model.dao;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.persistence.NoResultException;

import it.gestionebiglietti.model.Tessera;
import it.gestionebiglietti.model.Utente;

public class AziendaTrasportiDAO {

	public static void funzionamento() {
		Scanner scanner= new Scanner(System.in);
		funzionamento1(scanner);
	}
	
	public static void funzionamento1(Scanner scanner) {	
		System.out.println("");
		System.out.println("Quale operazione desideri effettuare?");
		System.out.println("");
		System.out.printf("DIGITA 1 PER RIVOLGERTI AD UN OPERATORE 👮,%n"
				+ "DIGITA 2 PER UTILIZZARE IL DISTRIBUTORE AUTOMATICO 🛒%n"
				+ "DIGITA 3 PER USCIRE DALLA STAZIONE 🚉%n");
		try {
			int scelta=Integer.valueOf(scanner.nextLine());
			if(scelta==1) {
				long idBi=1;
				operatore(scanner, idBi);
			}
			else if(scelta==2){
				sceltaDistributore(scanner);
			}
			else if(scelta==3){
				System.out.println("Arrivederci!");
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
	
	//PRIMA SCELTA: OPERATORE
	public static void operatore(Scanner scanner, long idBi) {
		System.out.println("");
		System.out.println("Buongiorno, sono Mario Rossi, come posso esserle utile? 👮");
		System.out.println("");
		System.out.printf("DIGITA 1 PER ACQUISTARE UNA TESSERA VIP 💳%n"
				+ "DIGITA 2 PER ACQUISTARE UN BIGLIETTO 🎫%n"
				+ "DIGITA 3 PER CONTROLLARE IL TUO ABBONAMENTO 🎫%n"
				+ "DIGITA 4 PER ACQUISTARE UN' ABBONAMENTO 🎫%n"
				+ "DIGITA 5 PER SELEZIONARE UN VIAGGIO 🏝️%n");
		int scelta=Integer.valueOf(scanner.nextLine());
		switch(scelta) {
		
		case 1:
			tessera(scanner);
			break;
			
		case 2:
			acquistoBiglietto(scanner, idBi);
			break;
			
		case 3:
			System.out.println("");
			System.out.println("Inserisci il tuo codice abbonamento");
			System.out.println("");
			try {
				String codice= scanner.nextLine();
				AbbonamentoDAO.checkValiditaAbbonamento(codice, scanner, idBi);
			}
			catch(NoResultException e) {
				System.out.println("Abbonamento non esistente");
				AziendaTrasportiDAO.funzionamento();
			}
			break;
			
		case 4:
			acquistoAbbonamento(scanner, idBi);
			break;
			
		case 5:
			sceltaBigliettoAbbonamento(scanner, idBi);
			break;
			
		default:
			System.out.println("Errore nella digitazione");
			AziendaTrasportiDAO.funzionamento();
			break;
		}
	}
	//PRIMA SCELTA: QUALE DISTRIBUTORE
	public static void sceltaDistributore(Scanner scanner) {
		System.out.println("");
		System.out.println("Seleziona un distributore 🛒");
		System.out.println("");
		System.out.printf("DIGITA 1 DISTRIBUTORE1 🛒%n"
				+ "DIGITA 2 DISTIBUTORE2 🛒%n"
				+ "DIGITA 3 DISTRIBUTORE3 🛒%n");
		try {
			int scelta=Integer.valueOf(scanner.nextLine());
			switch(scelta) {
			
			case 1:
				long idBi2=2;
				DistributoreAutomaticoDAO.checkDistributore(idBi2, scanner);
				break;
				
			case 2:
				long idBi3=3;
				DistributoreAutomaticoDAO.checkDistributore(idBi3, scanner);
				break;
				
			case 3:
				long idBi4=4;
				DistributoreAutomaticoDAO.checkDistributore(idBi4, scanner);
				break;
				
			default:
				System.out.println("Errore nella digitazione");
				AziendaTrasportiDAO.funzionamento();
				
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Errore nella digitazione");
			AziendaTrasportiDAO.funzionamento();
		}

		
	}
	
	//SECONDA SCELTA: DISTRIBUTORE FUNZIONANTE
	public static void distributore(long idBi, Scanner scanner) {
		System.out.println("");
		System.out.println("BENVENUTO");
		System.out.println("");
		System.out.printf("DIGITA 1 PER ACQUISTARE UNA TESSERA VIP 💳%n"
				+ "DIGITA 2 PER ACQUISTARE UN BIGLIETTO 🎫%n"
				+ "DIGITA 3 PER CONTROLLARE IL TUO ABBONAMENTO 🎫%n"
				+ "DIGITA 4 PER ACQUISTARE UN' ABBONAMENTO 🎫%n"
				+ "DIGITA 5 PER SELEZIONARE UN VIAGGIO 🏝️%n");
		int scelta=Integer.valueOf(scanner.nextLine());
		switch(scelta) {
		
		case 1:
			tessera(scanner);
			break;
			
		case 2:
			acquistoBiglietto(scanner, idBi);
			break;
			
		case 3:
			System.out.println("");
			System.out.println("Inserisci il tuo codice abbonamento");
			System.out.println("");
			try {
				String codice= scanner.nextLine();
				AbbonamentoDAO.checkValiditaAbbonamento(codice, scanner, idBi);
			}
			catch(NoResultException e) {
				System.out.println("Abbonamento non esistente");
				AziendaTrasportiDAO.funzionamento();
			}
			break;
			
		case 4:
			acquistoAbbonamento(scanner, idBi);
			break;
			
		case 5:
			sceltaBigliettoAbbonamento(scanner, idBi);
			break;
			
		default:
			System.out.println("Errore nella digitazione");
			AziendaTrasportiDAO.funzionamento();
			
		}
	}
	
	//SECONDA SCELTA: TESSERA
	public static void tessera(Scanner scanner) {
		System.out.println("");
		System.out.println("Inserisci il tuo nome");
		System.out.println("");
		String nome=scanner.nextLine();
		System.out.println("");
		System.out.println("Inserisci il tuo cognome");
		System.out.println("");
		String cognome=scanner.nextLine();
		Utente utente=UtenteDAO.salvaUtente(nome, cognome);
		int numTessera=TesseraDAO.generaNumTessera();
		TesseraDAO.checkNumeroTessera(numTessera, utente);
		AziendaTrasportiDAO.funzionamento();
	}
	
	//SECONDA SCELTA: BIGLIETTO
	public static void acquistoBiglietto(Scanner scanner, long idBi) {
		
		BigliettoDAO.controlloCodiceBiglietto(OggettoDaVidimareDAO.generaCodice());
		BiglietteriaDAO.incrementoBiglietti(idBi);
		AziendaTrasportiDAO.funzionamento();
	}
	
	//SECONDA SCELTA: ABBONAMENTO
	public static void acquistoAbbonamento(Scanner scanner, long idBi) {
		System.out.println("");
		System.out.println("Inserisci numero tessera");
		System.out.println("");
		try {
			int tessera=Integer.valueOf(scanner.nextLine());
			TesseraDAO.checkUtenteTessera(tessera, scanner, idBi);
		}
		catch(NumberFormatException e) {
			System.out.println("Errore nella digitazione");
			AziendaTrasportiDAO.funzionamento();
		}
		
	}
	
	public static void abbonamentoSceltaTemporale(Tessera tessera, Scanner scanner, long idBi) {
		System.out.println("");
		System.out.println("Seleziona un tipo di abbonamento");
		System.out.println("");
		System.out.printf("DIGITA 1 PER SETTIMANALE,%n"
				+ "DIGITA 2 PER MENSILE%n");
		int scelta=Integer.valueOf(scanner.nextLine());
		if(scelta==1) {
			LocalDate dataIni=LocalDate.now();
			LocalDate dataFine=dataIni.plusWeeks(1);
			AbbonamentoDAO.controlloCodiceAbbonamento(OggettoDaVidimareDAO.generaCodice(), 
					tessera, dataIni, dataFine);
			BiglietteriaDAO.incrementoAbbonamenti(idBi);
			AziendaTrasportiDAO.funzionamento();
		}
		else if(scelta==2) {
			LocalDate dataIni=LocalDate.now();
			LocalDate dataFine=dataIni.plusMonths(1);
			AbbonamentoDAO.controlloCodiceAbbonamento(OggettoDaVidimareDAO.generaCodice(), 
					tessera, dataIni, dataFine);
			BiglietteriaDAO.incrementoAbbonamenti(idBi);
			AziendaTrasportiDAO.funzionamento();
		}
		else {
			System.out.println("Errore nella digitazione");
			AziendaTrasportiDAO.funzionamento();
		}
	}
	
	
	public static void sceltaBigliettoAbbonamento(Scanner scanner, long idBi) {
		System.out.println("");
		System.out.println("Hai un biglietto o un abbonamento?");
		System.out.println("");
		System.out.printf("DIGITA 1 PER BIGLIETTO,%n"
				+ "DIGITA 2 PER ABBONAMENTO%n");
		int scelta=Integer.valueOf(scanner.nextLine());
		if(scelta==1) {
			System.out.println("");
			System.out.println("Inserisci il tuo codice biglietto");
			System.out.println("");
			String codice=scanner.nextLine();
			BigliettoDAO.checkUtenteBiglietto(codice, scanner);
		}
		else if(scelta==2) {
			AbbonamentoDAO.controlloPossessoAbbonamento(scanner, idBi);
		}
		else {
			System.out.println("Errore nella digitazione");
			AziendaTrasportiDAO.funzionamento();
		}
	}
}
