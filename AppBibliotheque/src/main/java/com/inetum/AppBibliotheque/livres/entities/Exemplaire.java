package com.inetum.AppBibliotheque.livres.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
@DiscriminatorValue(value = "Exemplaire") //valeur pour colonne "typeLivre"
public class Exemplaire extends Livre {
	
	public enum EtatLivre  {BON_ETAT, ABIME, HORS_SERVICE};
	@Enumerated(EnumType.STRING)
	
	
	private EtatLivre etat = EtatLivre.BON_ETAT;
	
	private Boolean disponibilite; // true si disponible(exemplaire.size !=0 et bon état), false si abimé ou hors service
	
	//COSNTRUCTEUR
	
	public Exemplaire(Long idLivre, String titre, String auteur, String editeur, Domaine domaine) {
		super(idLivre, titre, auteur, editeur, domaine);
		
	}


	@Override
	public String toString() {
		return "Exemplaire"+super.toString();
	}
	
	

}
