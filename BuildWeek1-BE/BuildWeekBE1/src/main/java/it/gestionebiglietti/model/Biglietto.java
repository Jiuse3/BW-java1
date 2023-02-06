package it.gestionebiglietti.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="biglietto")
@Getter
@Setter
@Entity
@NoArgsConstructor
@NamedQuery(name = "controlloCodiceBiglietto", query = "SELECT c.codUnivoco FROM "
		+ "Biglietto c WHERE c.codUnivoco = :valore")
@NamedQuery(name = "checkUtenteBiglietto", query = "SELECT b.codUnivoco FROM "
		+ "Biglietto b WHERE b.codUnivoco = :valore")
@NamedQuery(name = "recuperaIdBiglietto", query = "SELECT b.id FROM "
		+ "Biglietto b WHERE b.codUnivoco = :valore")
public class Biglietto extends OggettoDaVidimare{

	public Biglietto(String codUnivoco){
		super(codUnivoco);
	}
	
}
