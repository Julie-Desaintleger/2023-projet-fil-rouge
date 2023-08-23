package com.inetum.AppBibliotheque.personnes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class PersonneDto {
	private Long idPersonne;
	private String prenom;
	private String nom;
	private String email;
	private String telephone;
	private String adresse;

	public PersonneDto(Long idPersonne, String prenom, String nom, String email, String telephone,
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
