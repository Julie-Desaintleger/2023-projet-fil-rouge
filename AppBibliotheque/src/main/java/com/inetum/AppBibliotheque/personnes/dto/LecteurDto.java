package com.inetum.AppBibliotheque.personnes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LecteurDto extends PersonneDto {

	public LecteurDto(Long idPersonne, String prenom, String nom, String email, String telephone,
				String adresse) {
		super(idPersonne, prenom, nom, email, telephone, adresse);
	}

}
