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

@NamedQuery(name = "Domaine.findDomaineByNom", query = "SELECT d FROM Domaine d WHERE d.nom= ?1")

public class Domaine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domaine", cascade = CascadeType.ALL)
	// @OneToMany(fetch = FetchType.EAGER, mappedBy = "livre")
	private List<Livre> livres; // +get/set

	// CONSTRUSTEUR

	public Domaine(Long id, String nom, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
	}

	// TOSTRING
	@Override
	public String toString() {
		return "Domaine [id=" + id + ", nom=" + nom + ", description=" + description + "]";
	}

}
