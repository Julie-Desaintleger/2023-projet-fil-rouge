package com.inetum.AppBibliotheque.livres.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity

@NamedQuery(name = "Exemplaire.getDisponibilite", query = "SELECT e FROM Exemplaire e WHERE e.etat <> ?1")

public class Exemplaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idExemp;

	public enum EtatLivre {
		BON_ETAT, ABIME, HORS_SERVICE
	};

	@Enumerated(EnumType.STRING)

	private EtatLivre etat = EtatLivre.BON_ETAT;

	private Boolean isDisponibilite; // true si getDisponibilite().size !=0 sinon false

	private String label;

	@ManyToOne // Many exemplaire To one Livre
	@JoinColumn(name = "id_livre")
	private Livre livre;

	// COSNTRUCTEUR

	public Exemplaire(Long idExemp, EtatLivre etat, String label, Livre livre) {
		super();
		this.idExemp = idExemp;
		this.etat = etat;
		this.label = label;
		this.livre = livre;
	}

	// TO STRING

	@Override
	public String toString() {
		return "Exemplaire [etat=" + etat + ", disponibilite=" + isDisponibilite + ", idExemp=" + idExemp + ", label="
				+ label + "]";
	}

}
