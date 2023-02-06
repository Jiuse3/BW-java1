package it.gestionebiglietti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="utente")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cognome;
	
	public Utente(String nome, String cognome) {
		this.nome=nome;
		this.cognome=cognome;
	}
	
}
