package com.inetum.AppBibliotheque.livres.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class DomaineDto {
	private Long id;
	private String nom;
	private String description;
	
	
	
	public DomaineDto(Long id, String nom, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
	}
	
	

}
