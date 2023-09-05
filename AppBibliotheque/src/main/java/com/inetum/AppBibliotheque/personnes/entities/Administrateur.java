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
@DiscriminatorValue(value = "Administrateur")
public class Administrateur extends Personne {

	private String username;

	public Administrateur(Long idPersonne, String prenom, String nom, String email, String telephone,
				String adresse, String username) {
		super(idPersonne, prenom, nom, email, telephone, adresse);
		this.username = username;
	}

	public Administrateur(Long idPersonne, String prenom, String nom, String email, String password,
				String telephone, String adresse, String username) {
		super(idPersonne, prenom, nom, email, password, telephone, adresse);
		this.username = username;
	}

	@Override
	public String toString() {
		return "Administrateur [username=" + username + ", heritant de Personne=" + super.toString()
					+ "]";
	}

}
