package com.inetum.AppBibliotheque.personnes.services;

import java.util.List;

import com.inetum.AppBibliotheque.personnes.dto.LecteurDto;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServiceLecteur extends IGenericService<Lecteur, Long, LecteurDto> {
	Lecteur rechercherPersonneParEmail(String email);

	List<Lecteur> rechercherPersonneParNom(String nom);

	List<Lecteur> rechercherPersonneParNomEtPrenom(String nom, String prenom);

	LecteurDto saveOrUpdateDto(LecteurDto lecteurDto);

}
