package com.inetum.AppBibliotheque.livres.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LivreDto {
	private Long idLivre;
	private String titre;
	private String auteur;
	private String editeur;
	
	public LivreDto(Long idLivre, String titre, String auteur, String editeur) {
		super();
		this.idLivre = idLivre;
		this.titre = titre;
		this.auteur = auteur;
		this.editeur = editeur;
	}
	
	

}
