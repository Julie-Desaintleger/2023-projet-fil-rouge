package com.inetum.AppBibliotheque.livres.dao.interfaces;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire.EtatLivre;

public interface IDaoExemplaire extends JpaRepository<Exemplaire, Long>{
	
	List<Exemplaire> getDisponibilite(EtatLivre etat);
	//List<Exemplaire> searchByTitleExemp(String titre);  //avec @NamedQuery("Exemplaire.searchByTitleExemp")
	List<Exemplaire> findByLivreTitre(String titre); //findByBookTitle in english

}
