package com.inetum.AppBibliotheque.personnes.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.AppBibliotheque.personnes.entities.Lecteur;

public interface IDaoLecteur extends JpaRepository<Lecteur, Long> {
	Lecteur findByEmail(String email);

	List<Lecteur> findByNom(String nom);

	List<Lecteur> findByNomAndPrenom(String nom, String prenom);

}
