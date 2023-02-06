package it.gestionebiglietti.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="rivenditore_fisico")
@Getter
@Setter
@Entity
@NoArgsConstructor
public class RivenditoreFisico extends Biglietteria{
	
}
