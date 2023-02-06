package it.gestionebiglietti.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="tessera")
@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "checkUtenteTessera", query = "SELECT t.numTessera FROM Tessera t "
		+ "WHERE t.numTessera = :valore")
@NamedQuery(name = "checkNumeroTessera", query = "SELECT t.numTessera FROM Tessera t "
		+ "WHERE t.numTessera = :valore")
@NamedQuery(name = "recuperaTessera", query = "SELECT t FROM Tessera t "
		+ "WHERE t.numTessera = :valore")
public class Tessera {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int numTessera;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="id_utente")
	private Utente utente;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	
	
	public Tessera(int numTessera, Utente utente, LocalDate dataInizio, LocalDate dataFine) {
		this.numTessera=numTessera;
		this.utente=utente;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}
}
