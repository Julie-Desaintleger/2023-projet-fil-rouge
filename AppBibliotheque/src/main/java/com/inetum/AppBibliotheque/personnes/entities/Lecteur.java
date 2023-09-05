package com.inetum.AppBibliotheque.personnes.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "Lecteur")
public class Lecteur extends Personne {

	@Override
	public String toString() {
		return "Lecteur [heritant de Personne=" + super.toString() + "]";
	}

	public Lecteur(Long idPersonne, String prenom, String nom, String email, String telephone,
				String adresse) {
		super(idPersonne, prenom, nom, email, telephone, adresse);
	}

	public Lecteur(Long idPersonne, String prenom, String nom, String email, String password,
				String telephone, String adresse) {
		super(idPersonne, prenom, nom, email, password, telephone, adresse);
	}

}
