package com.inetum.AppBibliotheque.livres.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.AppBibliotheque.livres.entities.Livre;

public interface IDaoLivre extends JpaRepository<Livre, Long>{
	
	Livre findByTitre(String titre);

}
