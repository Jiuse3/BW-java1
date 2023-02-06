package it.gestionemezzi.model;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="mezziditrasporto")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class MezziDiTrasporto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)
	private TipoMezzi tipoMezzi;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "tratta_id", referencedColumnName = "id")
	private Tratta tratta;
	private LocalTime oraPartenza;
	private LocalTime oraArrivo;
	private int capienza;
	private boolean servizio;
	private int traccia;
	
	public MezziDiTrasporto(TipoMezzi tipoMezzi, Tratta tratta, LocalTime oraPartenza,
			int capienza, boolean servizio) {
		super();
		this.tipoMezzi = tipoMezzi;
		this.tratta = tratta;
		this.oraPartenza = oraPartenza;
		this.oraArrivo = oraPartenza.plusHours(getTratta().getDurataTratta());
		this.capienza = capienza;
		this.servizio = servizio;
	}
	
	@Override
	public String toString() {
		return "NumeroMezzo=" + id + ", TipoMezzo=" + tipoMezzi + ", " + tratta + " | Partenza: " + oraPartenza + ", Arrivo: " + oraArrivo + ", Servizio: " + servizio;
		
	}
	
}
