package com.inetum.AppBibliotheque.emprunts.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;

public interface IDaoEmprunt extends JpaRepository<Emprunter, Long> {
	
	List<Emprunter> findByemprunteurIdPersonne(Long idPers);

}
