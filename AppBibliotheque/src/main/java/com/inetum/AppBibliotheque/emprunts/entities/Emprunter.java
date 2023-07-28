package com.inetum.AppBibliotheque.emprunts.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "Emprunter.findAll", query = "SELECT emp FROM Emprunter emp")
public class Emprunter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	public enum TypeEmprunt {
		RESERVE, EFFECTIF
	};

	@Enumerated(EnumType.STRING)
	private TypeEmprunt type;

	@ManyToOne
	@JoinColumn(name = "id_personne")
	private Lecteur emprunteur;
	@ManyToOne
	@JoinColumn(name = "id_exemp")
	private Exemplaire exemplaireEmprunte;

	public Emprunter(Long id, Date dateDebut, Date dateFin, TypeEmprunt type, Lecteur emprunteur,
				Exemplaire exemplaireEmprunte) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.emprunteur = emprunteur;
		this.exemplaireEmprunte = exemplaireEmprunte;
	}

	@Override
	public String toString() {
		return "Emprunter [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", type="
					+ type + ", id emprunteur=" + emprunteur.getIdPersonne() + ", id exemplaireEmprunte="
					+ exemplaireEmprunte.getIdExemp() + "]";
	}

}
