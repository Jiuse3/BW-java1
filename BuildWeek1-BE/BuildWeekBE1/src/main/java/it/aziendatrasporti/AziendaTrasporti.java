package it.aziendatrasporti;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.gestionebiglietti.model.Abbonamento;
import it.gestionebiglietti.model.Biglietto;
import it.gestionebiglietti.model.DistributoreAutomatico;
import it.gestionebiglietti.model.RivenditoreFisico;
import it.gestionebiglietti.model.Tessera;
import it.gestionebiglietti.model.Utente;
import it.gestionebiglietti.model.dao.AbbonamentoDAO;
import it.gestionebiglietti.model.dao.AziendaTrasportiDAO;
import it.gestionebiglietti.model.dao.BiglietteriaDAO;
import it.gestionebiglietti.model.dao.BigliettoDAO;
import it.gestionebiglietti.model.dao.DistributoreAutomaticoDAO;
import it.gestionebiglietti.model.dao.RivenditoreFisicoDAO;
import it.gestionemezzi.model.Arrivi;
import it.gestionemezzi.model.MezziDiTrasporto;
import it.gestionemezzi.model.Partenze;
import it.gestionemezzi.model.TipoMezzi;
import it.gestionemezzi.model.Tratta;
import it.gestionemezzi.model.dao.GestioneMezziDAO;

public class AziendaTrasporti {
	
	private static final String aziendaTrasporti = "BuildWeekBE1";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(aziendaTrasporti);
	private static final EntityManager em = emf.createEntityManager();
	private static final EntityTransaction t = em.getTransaction();

	public static void main(String[] args) {

		Tratta como_bologna = new Tratta(Partenze.COMO, Arrivi.BOLOGNA, 2);
		Tratta como_genova = new Tratta(Partenze.COMO, Arrivi.GENOVA, 3);
		Tratta como_napoli = new Tratta(Partenze.COMO, Arrivi.NAPOLI, 5);
		Tratta como_torino = new Tratta(Partenze.COMO, Arrivi.TORINO, 1);
		Tratta como_venezia = new Tratta(Partenze.COMO, Arrivi.VENEZIA, 4);
		
		Tratta firenze_bologna = new Tratta(Partenze.FIRENZE, Arrivi.BOLOGNA, 2);
		Tratta firenze_genova = new Tratta(Partenze.FIRENZE, Arrivi.GENOVA, 3);
		Tratta firenze_napoli = new Tratta(Partenze.FIRENZE, Arrivi.NAPOLI, 4);
		Tratta firenze_torino = new Tratta(Partenze.FIRENZE, Arrivi.TORINO, 2);
		Tratta firenze_venezia = new Tratta(Partenze.FIRENZE, Arrivi.VENEZIA, 3);
		
		Tratta milano_bologna = new Tratta(Partenze.MILANO, Arrivi.BOLOGNA, 3);
		Tratta milano_genova = new Tratta(Partenze.MILANO, Arrivi.GENOVA, 3);
		Tratta milano_napoli = new Tratta(Partenze.MILANO, Arrivi.NAPOLI, 6);
		Tratta milano_torino = new Tratta(Partenze.MILANO, Arrivi.TORINO, 1);
		Tratta milano_venezia = new Tratta(Partenze.MILANO, Arrivi.VENEZIA, 2);
		
		Tratta padova_bologna = new Tratta(Partenze.PADOVA, Arrivi.BOLOGNA, 2);
		Tratta padova_genova = new Tratta(Partenze.PADOVA, Arrivi.GENOVA, 4);
		Tratta padova_napoli = new Tratta(Partenze.PADOVA, Arrivi.NAPOLI, 5);
		Tratta padova_torino = new Tratta(Partenze.PADOVA, Arrivi.TORINO, 4);
		Tratta padova_venezia = new Tratta(Partenze.PADOVA, Arrivi.VENEZIA, 1);
		
		Tratta roma_bologna = new Tratta(Partenze.ROMA, Arrivi.BOLOGNA, 3);
		Tratta roma_genova = new Tratta(Partenze.ROMA, Arrivi.GENOVA, 3);
		Tratta roma_napoli = new Tratta(Partenze.ROMA, Arrivi.NAPOLI, 2);
		Tratta roma_torino = new Tratta(Partenze.ROMA, Arrivi.TORINO, 5);
		Tratta roma_venezia = new Tratta(Partenze.ROMA, Arrivi.VENEZIA, 4);

		MezziDiTrasporto bus1 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, como_bologna, LocalTime.of(12, 30), 70,false);
		MezziDiTrasporto bus2 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, como_genova, LocalTime.of(6, 00), 90, true);
		MezziDiTrasporto bus3 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, como_napoli, LocalTime.of(10, 30), 130, true);
		MezziDiTrasporto bus4 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, como_torino, LocalTime.of(14, 00), 58, true);
		MezziDiTrasporto bus5 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, como_venezia, LocalTime.of(17, 30), 70, true);
		
		MezziDiTrasporto bus6 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, firenze_bologna, LocalTime.of(5, 30), 80,true);
		MezziDiTrasporto bus7 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, firenze_genova, LocalTime.of(4, 00), 98,true);
		MezziDiTrasporto bus8 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, firenze_napoli, LocalTime.of(10, 30), 160,false);
		MezziDiTrasporto bus9 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, firenze_torino, LocalTime.of(20, 00), 45,true);
		MezziDiTrasporto bus10 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, firenze_venezia, LocalTime.of(19, 30), 180,true);

		MezziDiTrasporto bus11 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, padova_bologna, LocalTime.of(6, 00), 40, true);
		MezziDiTrasporto bus12 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, padova_genova, LocalTime.of(8, 00), 94, true);
		MezziDiTrasporto bus13 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, padova_napoli, LocalTime.of(10, 30), 120, true);
		MezziDiTrasporto bus14 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, padova_torino, LocalTime.of(16, 00), 55, false);
		MezziDiTrasporto bus15 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, padova_venezia, LocalTime.of(11, 30), 150, true);
		
		MezziDiTrasporto bus16 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, milano_bologna, LocalTime.of(8, 30), 70,true);
		MezziDiTrasporto bus17 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, milano_genova, LocalTime.of(9, 00), 90,false);
		MezziDiTrasporto bus18 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, milano_napoli, LocalTime.of(17, 30), 80,true);
		MezziDiTrasporto bus19 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, milano_torino, LocalTime.of(14, 30), 58,true);
		MezziDiTrasporto bus20 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, milano_venezia, LocalTime.of(19, 30), 60,true);
		
		MezziDiTrasporto bus21 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, roma_bologna, LocalTime.of(9, 30), 68,true);
		MezziDiTrasporto bus22 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, roma_genova, LocalTime.of(10, 00), 95,true);
		MezziDiTrasporto bus23 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, roma_napoli, LocalTime.of(12, 30), 80,true);
		MezziDiTrasporto bus24 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, roma_torino, LocalTime.of(17, 00), 56,true);
		MezziDiTrasporto bus25 = new MezziDiTrasporto(TipoMezzi.AUTOBUS, roma_venezia, LocalTime.of(15, 30), 70, false);
		
		MezziDiTrasporto tram1 = new MezziDiTrasporto(TipoMezzi.TRAM, milano_bologna, LocalTime.of(8, 30), 70,true);
		MezziDiTrasporto tram2 = new MezziDiTrasporto(TipoMezzi.TRAM, milano_genova, LocalTime.of(7, 30), 90,true);
		MezziDiTrasporto tram3 = new MezziDiTrasporto(TipoMezzi.TRAM, milano_napoli, LocalTime.of(10, 30), 80,false);
		MezziDiTrasporto tram4 = new MezziDiTrasporto(TipoMezzi.TRAM, milano_torino, LocalTime.of(14, 00), 58,true);
		MezziDiTrasporto tram5 = new MezziDiTrasporto(TipoMezzi.TRAM, milano_venezia, LocalTime.of(15, 30), 60,true);
		
		MezziDiTrasporto tram6 = new MezziDiTrasporto(TipoMezzi.TRAM, roma_bologna, LocalTime.of(00, 30), 68,false);
		MezziDiTrasporto tram7 = new MezziDiTrasporto(TipoMezzi.TRAM, roma_genova, LocalTime.of(11, 00), 95,true);
		MezziDiTrasporto tram8 = new MezziDiTrasporto(TipoMezzi.TRAM, roma_napoli, LocalTime.of(02, 30), 80,true);
		MezziDiTrasporto tram9 = new MezziDiTrasporto(TipoMezzi.TRAM, roma_torino, LocalTime.of(18, 30), 56,true);
		MezziDiTrasporto tram10 = new MezziDiTrasporto(TipoMezzi.TRAM, roma_venezia, LocalTime.of(13, 30), 70, true);
		
		MezziDiTrasporto tram11 = new MezziDiTrasporto(TipoMezzi.TRAM, como_bologna, LocalTime.of(13, 30), 70,true);
		MezziDiTrasporto tram12 = new MezziDiTrasporto(TipoMezzi.TRAM, como_genova, LocalTime.of(6, 00), 90, true);
		MezziDiTrasporto tram13 = new MezziDiTrasporto(TipoMezzi.TRAM, como_napoli, LocalTime.of(10, 30), 130, true);
		MezziDiTrasporto tram14 = new MezziDiTrasporto(TipoMezzi.TRAM, como_torino, LocalTime.of(14, 00), 58, true);
		MezziDiTrasporto tram15 = new MezziDiTrasporto(TipoMezzi.TRAM, como_venezia, LocalTime.of(17, 30), 70, false);
		
		MezziDiTrasporto tram16 = new MezziDiTrasporto(TipoMezzi.TRAM, firenze_bologna, LocalTime.of(7, 30), 80,true);
		MezziDiTrasporto tram17 = new MezziDiTrasporto(TipoMezzi.TRAM, firenze_genova, LocalTime.of(8, 30), 98,false);
		MezziDiTrasporto tram18 = new MezziDiTrasporto(TipoMezzi.TRAM, firenze_napoli, LocalTime.of(04, 30), 160,true);
		MezziDiTrasporto tram19 = new MezziDiTrasporto(TipoMezzi.TRAM, firenze_torino, LocalTime.of(16, 00), 45,true);
		MezziDiTrasporto tram20 = new MezziDiTrasporto(TipoMezzi.TRAM, firenze_venezia, LocalTime.of(10, 30), 180,true);
		
		MezziDiTrasporto tram21 = new MezziDiTrasporto(TipoMezzi.TRAM, padova_bologna, LocalTime.of(5, 00), 40, false);
		MezziDiTrasporto tram22 = new MezziDiTrasporto(TipoMezzi.TRAM, padova_genova, LocalTime.of(6, 00), 94, true);
		MezziDiTrasporto tram23 = new MezziDiTrasporto(TipoMezzi.TRAM, padova_napoli, LocalTime.of(12, 40), 120, true);
		MezziDiTrasporto tram24 = new MezziDiTrasporto(TipoMezzi.TRAM, padova_torino, LocalTime.of(14, 30), 55, true);
		MezziDiTrasporto tram25 = new MezziDiTrasporto(TipoMezzi.TRAM, padova_venezia, LocalTime.of(7, 30), 150, true);

		GestioneMezziDAO gm = new GestioneMezziDAO();

		
		// PER ISTANZIARE DISTRUBUTORI  E RIVENDITORI
		
//		RivenditoreFisicoDAO.istanziaRivenditoreFisico();
//		DistributoreAutomaticoDAO.istanziaDistributori();
		
		// PER ISTANZIARE MEZZI E TRATTE
		
//		gm.insertMezzi(bus1);
//	    gm.insertMezzi(bus2);
//	    gm.insertMezzi(bus3);
//	    gm.insertMezzi(bus4);
//	    gm.insertMezzi(bus5);
//	    gm.insertMezzi(bus6);
//	    gm.insertMezzi(bus7);
//	    gm.insertMezzi(bus8);
//	    gm.insertMezzi(bus9);
//	    gm.insertMezzi(bus10);
//	    gm.insertMezzi(bus11);
//	    gm.insertMezzi(bus12);
//	    gm.insertMezzi(bus13);
//	    gm.insertMezzi(bus14);
//	    gm.insertMezzi(bus15);
//	    gm.insertMezzi(bus16);
//	    gm.insertMezzi(bus17);
//	    gm.insertMezzi(bus18);
//	    gm.insertMezzi(bus19);
//	    gm.insertMezzi(bus20);
//	    gm.insertMezzi(bus21);
//	    gm.insertMezzi(bus22);
//	    gm.insertMezzi(bus23);
//	    gm.insertMezzi(bus24);
//	    gm.insertMezzi(bus25);
//	    gm.insertMezzi(tram1);
//	    gm.insertMezzi(tram2);
//	    gm.insertMezzi(tram3);
//	    gm.insertMezzi(tram4);
//	    gm.insertMezzi(tram5);
//	    gm.insertMezzi(tram6);
//	    gm.insertMezzi(tram7);
//	    gm.insertMezzi(tram8);
//	    gm.insertMezzi(tram9);
//	    gm.insertMezzi(tram10);
//	    gm.insertMezzi(tram11);
//	    gm.insertMezzi(tram12);
//	    gm.insertMezzi(tram13);
//	    gm.insertMezzi(tram14);
//	    gm.insertMezzi(tram15);
//	    gm.insertMezzi(tram16);
//	    gm.insertMezzi(tram17);
//	    gm.insertMezzi(tram18);
//	    gm.insertMezzi(tram19);
//	    gm.insertMezzi(tram20);
//	    gm.insertMezzi(tram21);
//	    gm.insertMezzi(tram22);
//	    gm.insertMezzi(tram23);
//	    gm.insertMezzi(tram24);
//	    gm.insertMezzi(tram25);
		
		//PARTE DELLO SCANNER
		System.out.println("Buongiorno, benvenuto in Flixbus");
		AziendaTrasportiDAO.funzionamento();
			
	}

}
