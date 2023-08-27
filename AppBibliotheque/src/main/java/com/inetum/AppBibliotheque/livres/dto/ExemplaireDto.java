package com.inetum.AppBibliotheque.livres.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ExemplaireDto {

	private Long idExemp;

	

	private String etat = "BON_ETAT";

	private Boolean isDisponibilite; 

	private String label;

	public ExemplaireDto(Long idExemp, String etat, Boolean isDisponibilite, String label) {
		super();
		this.idExemp = idExemp;
		this.etat = etat;
		this.isDisponibilite = isDisponibilite;
		this.label = label;
	}
	
	
	
}
