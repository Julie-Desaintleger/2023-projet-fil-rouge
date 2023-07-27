package com.inetum.AppBibliotheque.personnes.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoAdministrateur;
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoLecteur;
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoPersonne;
import com.inetum.AppBibliotheque.personnes.entities.Administrateur;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;
import com.inetum.AppBibliotheque.personnes.entities.Personne;

@SpringBootTest // classe interprétée par JUnit et SpringBoot
public class TestPersonneDao {

	Logger logger = LoggerFactory.getLogger(TestPersonneDao.class);

	@Autowired // pour initialisation daoCompte
	// qui va référencer un composant Spring existant compatible
	// avec l'interface DaoCompte (DaoCompteJpa avec@Repository)
	private IDaoPersonne daoPersonneJpa;

	@Autowired
	private IDaoAdministrateur daoAdministrateurJpa;

	@Autowired
	private IDaoLecteur daoLecteurJpa;

	@Test
	public void testPersonnesAdminLecteur() {
		daoPersonneJpa.insert(new Personne(null, "Jean", "Valjean", "jean@inetum.com", "0102030405",
					"rue de la maison"));
		daoPersonneJpa.insert(new Personne(null, "Gerard", "Duchemin", "gerard@inetum.com", "0102030405",
					"rue de la maison"));
		daoAdministrateurJpa.insert(new Administrateur(null, "Roger", "Theboss", "roger@biblio.com",
					null, null, "rogeradmin", "1234"));
		daoLecteurJpa.insert(new Lecteur(null, "Benoit", "Georges", null, null, null));

		List<Personne> personnes = daoPersonneJpa.findAll();
		assertTrue(personnes.size() == 4);

		logger.trace("Liste de personnes=" + personnes);

		List<Administrateur> admins = daoAdministrateurJpa.findAll();
		assertTrue(admins.size() == 1);

		logger.trace("Liste d'administrateurs=" + admins);

		List<Lecteur> lecteurs = daoLecteurJpa.findAll();
		assertTrue(lecteurs.size() == 1);

		logger.trace("Liste de lecteurs=" + lecteurs);
	}

}
