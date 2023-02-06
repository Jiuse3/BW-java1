package it.gestionebiglietti.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="abbonamento")
@Getter
@Setter
@Entity
@NoArgsConstructor
@NamedQuery(name = "checkUtenteAbbonamento", query = "SELECT a.codUnivoco FROM Abbonamento a "
		+ "WHERE a.codUnivoco = :valore")
public class Abbonamento extends OggettoDaVidimare{

	private LocalDate dataEmissione;
	private LocalDate dataScandenza;
	@OneToOne
	private Tessera tessera;
	
	public Abbonamento(String codUnivoco, LocalDate dataEmissione,
			LocalDate dataScadenza, Tessera tessera) {
		super(codUnivoco);
		this.dataEmissione=dataEmissione;
		this.dataScandenza=dataScadenza;
		this.tessera=tessera;
	}
	
}
