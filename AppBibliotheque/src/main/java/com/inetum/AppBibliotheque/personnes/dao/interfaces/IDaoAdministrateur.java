package com.inetum.AppBibliotheque.personnes.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.AppBibliotheque.personnes.entities.Administrateur;

public interface IDaoAdministrateur extends JpaRepository<Administrateur, Long> {

}
