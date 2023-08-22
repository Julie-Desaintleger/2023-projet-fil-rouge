package com.inetum.AppBibliotheque.personnes.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.AppBibliotheque.personnes.entities.Personne;

public interface IDaoPersonne extends JpaRepository<Personne, Long> {

}
