package com.inetum.AppBibliotheque.personnes.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.AppBibliotheque.personnes.entities.Personne;

public interface IDaoPersonne extends JpaRepository<Personne, Long> {
	Personne findByEmail(String email);

	List<Personne> findByNom(String nom);

	List<Personne> findByNomAndPrenom(String nom, String prenom);

	Personne findByEmailAndPassword(String email, String password);

}
