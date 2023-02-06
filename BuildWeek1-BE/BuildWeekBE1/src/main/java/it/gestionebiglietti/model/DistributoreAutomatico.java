package it.gestionebiglietti.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="distibutore_automatico")
@Getter
@Setter
@Entity
@NoArgsConstructor
public class DistributoreAutomatico extends Biglietteria{
	
	private boolean inServizio;
	
	public DistributoreAutomatico(boolean inServizio) {
		this.inServizio=inServizio;
	}
}
