package it.gestionemezzi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tratte")
@Getter
@Setter
@NoArgsConstructor
public class Tratta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private Partenze partenza;
	@Enumerated(EnumType.STRING)
	private Arrivi arrivi;
	private int durataTratta;
	@OneToMany(mappedBy = "tratta")
	private List<MezziDiTrasporto> mezzo;
	
	public Tratta(Partenze partenza, Arrivi arrivi, int durataTratta) {
		super();
		this.partenza = partenza;
		this.arrivi = arrivi;
		this.durataTratta = durataTratta;
	}
	
	@Override
	public String toString() {
		return partenza.toString() + " - " + arrivi.toString() + ", Durata viaggio=" + durataTratta + "h";
		
	}
	
	
	
}
