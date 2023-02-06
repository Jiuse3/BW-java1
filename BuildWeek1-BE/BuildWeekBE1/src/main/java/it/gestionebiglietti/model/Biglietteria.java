package it.gestionebiglietti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="biglietteria")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Biglietteria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int bigliettiEmessi;
	private int abbonamentiEmessi;
	
}
