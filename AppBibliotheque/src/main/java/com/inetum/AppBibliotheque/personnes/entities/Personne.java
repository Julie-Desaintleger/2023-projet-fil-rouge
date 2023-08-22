package com.inetum.AppBibliotheque.personnes.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typePersonne", discriminatorType = DiscriminatorType.STRING)
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonne;
	private String prenom;
	private String nom;
	private String email;
	private String telephone;
	private String adresse;

	@Override
	public String toString() {
		return "Personne [idPersonne=" + idPersonne + ", prenom=" + prenom + ", nom=" + nom + ", email="
					+ email + ", telephone=" + telephone + ", adresse=" + adresse + "]";
	}

	public Personne(Long idPersonne, String prenom, String nom, String email, String telephone,
				String adresse) {
		super();
		this.idPersonne = idPersonne;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
	}

}
