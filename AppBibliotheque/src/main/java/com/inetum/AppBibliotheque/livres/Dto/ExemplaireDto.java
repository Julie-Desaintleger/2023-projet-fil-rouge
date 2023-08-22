package com.inetum.AppBibliotheque.livres.Dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ExemplaireDto {

	private Long idExemp;

	public enum EtatLivre {
		BON_ETAT, ABIME, HORS_SERVICE
	};

	@Enumerated(EnumType.STRING)

	private EtatLivre etat = EtatLivre.BON_ETAT;

	private Boolean isDisponibilite; 

	private String label;

	public ExemplaireDto(Long idExemp, EtatLivre etat, Boolean isDisponibilite, String label) {
		super();
		this.idExemp = idExemp;
		this.etat = etat;
		this.isDisponibilite = isDisponibilite;
		this.label = label;
	}
	
	
	
}
