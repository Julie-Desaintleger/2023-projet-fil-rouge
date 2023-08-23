package com.inetum.AppBibliotheque.personnes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class AdministrateurDto extends PersonneDto {

	private String username;
	private String password;

	public AdministrateurDto(Long idPersonne, String prenom, String nom, String email, String telephone,
				String adresse, String username, String password) {
		super(idPersonne, prenom, nom, email, telephone, adresse);
		this.username = username;
		this.password = password;
	}

}
