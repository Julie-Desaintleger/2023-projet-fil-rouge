package com.inetum.AppBibliotheque.livres.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity

@NamedQuery(name = "Livre.findLivreByTitre", 
query = "SELECT l FROM Livre l WHERE l.titre= ?1")
@DiscriminatorColumn(name = "typeLivre", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "Livre")
public class Livre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLivre;
	private String titre;
	private String auteur;
	private String editeur;
	
	@ManyToOne //Many Operation To one Compte
	  @JoinColumn(name = "id_domaine")
	private Domaine domaine;
	
	//CONSTRUSTEUR
	public Livre(Long idLivre, String titre, String auteur, String editeur, Domaine domaine) {
		super();
		this.idLivre = idLivre;
		this.titre = titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.domaine = domaine;
	}

	
	
	//TOSTRING
	
	@Override
	public String toString() {
		return "Livre [idLivre=" + idLivre + ", titre=" + titre + ", auteur=" + auteur + ", editeur=" + editeur + "]";
	}
	

}
