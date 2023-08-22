package com.inetum.AppBibliotheque.livres.dao.interfaces;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.AppBibliotheque.livres.entities.Domaine;



public interface IDaoDomaine extends  JpaRepository<Domaine, Long>{

	List<Domaine> findByNom(String nom);
	
	

}
