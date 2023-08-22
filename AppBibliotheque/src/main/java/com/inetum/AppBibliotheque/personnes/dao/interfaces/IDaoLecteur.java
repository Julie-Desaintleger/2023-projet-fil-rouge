package com.inetum.AppBibliotheque.personnes.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.AppBibliotheque.personnes.entities.Lecteur;

public interface IDaoLecteur extends JpaRepository<Lecteur, Long> {

}
