package com.inetum.AppBibliotheque.livres.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LivreDto {
	private Long idLivre;
	private String titre;
	private String auteur;
	private String editeur;
	private Long idDomaine;
	
	public LivreDto(Long idLivre, String titre, String auteur, String editeur, Long idDomaine) {
		super();
		this.idLivre = idLivre;
		this.titre = titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.idDomaine = idDomaine;
	}
	public LivreDto(Long idLivre, String titre, String auteur, String editeur) {
		this(idLivre, titre, auteur, editeur, null);
	}
	
	

}
