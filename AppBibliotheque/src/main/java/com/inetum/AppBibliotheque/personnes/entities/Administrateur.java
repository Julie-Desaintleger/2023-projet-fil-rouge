package com.inetum.AppBibliotheque.personnes.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQuery(name = "Administrateur.findAll", query = "SELECT a FROM Administrateur a")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "Administrateur")
public class Administrateur extends Personne {

	private String username;
	private String password;

	public Administrateur(Long idPersonne, String prenom, String nom, String email, String telephone,
				String adresse, String username, String password) {
		super(idPersonne, prenom, nom, email, telephone, adresse);
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrateur [username=" + username + ", password=" + password
					+ ", heritant de Personne=" + super.toString() + "]";
	}

}
