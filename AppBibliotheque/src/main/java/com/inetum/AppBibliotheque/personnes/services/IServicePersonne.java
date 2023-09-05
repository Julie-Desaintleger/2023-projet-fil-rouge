package com.inetum.AppBibliotheque.personnes.services;

import java.util.List;

import com.inetum.AppBibliotheque.personnes.dto.LoginRequest;
import com.inetum.AppBibliotheque.personnes.dto.LoginResponse;
import com.inetum.AppBibliotheque.personnes.dto.PersonneDto;
import com.inetum.AppBibliotheque.personnes.entities.Personne;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServicePersonne extends IGenericService<Personne, Long, PersonneDto> {

	Personne rechercherPersonneParEmail(String email);

	Personne rechercherPersonneParEmailEtPassword(String email, String password);

	List<Personne> rechercherPersonneParNom(String nom);

	List<Personne> rechercherPersonneParNomEtPrenom(String nom, String prenom);

	PersonneDto saveOrUpdateDto(PersonneDto personneDto);

	LoginResponse verifLogin(LoginRequest loginRequest);

}
