package com.inetum.AppBibliotheque.livres.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity

@NamedQuery(name = "Livre.findLivreByTitre", query = "SELECT l FROM Livre l WHERE l.titre= ?1")

public class Livre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLivre;
	private String titre;
	private String auteur;
	private String editeur;
	private Integer nbExemp;

	@ManyToOne // Many livre To one domaine
	@JoinColumn(name = "id_domaine")
	private Domaine domaine;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "livre", cascade = CascadeType.ALL)
	// @OneToMany(fetch = FetchType.EAGER, mappedBy = "livre")
	private List<Exemplaire> exemplaires; // +get/set
	
	// CONSTRUSTEUR
	public Livre(Long idLivre, String titre, String auteur, String editeur, Integer nbExemp, Domaine domaine) {
		super();
		this.idLivre = idLivre;
		this.titre = titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.nbExemp = nbExemp;
		this.domaine = domaine;
	}

	// TOSTRING

	@Override
	public String toString() {
		return "Livre [idLivre=" + idLivre + ", titre=" + titre + ", auteur=" + auteur + ", editeur=" + editeur + "]";
	}

}
