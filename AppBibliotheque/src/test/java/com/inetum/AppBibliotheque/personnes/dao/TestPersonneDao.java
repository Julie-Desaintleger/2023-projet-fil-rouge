package com.inetum.AppBibliotheque.personnes.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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

/*
 * La classe TestPersonneDao teste l'ensemble du package 
 * com.inetum.AppBibliotheque.personnes.dao.interfaces 
 * qui etends JpaRepository<Entity, PrimaryKey>
 * 
 * Il teste alors le CRUD pour :
 *   - une personne,
 *   - un administrateur,
 *   - un lecteur
 */
@SpringBootTest // classe interprétée par JUnit et SpringBoot
public class TestPersonneDao {

	Logger logger = LoggerFactory.getLogger(TestPersonneDao.class);

	@Autowired // pour initialisation daoPersonne
	// qui va référencer un composant Spring existant compatible
	// avec l'interface DaoCompte (DaoCompteJpa avec@Repository)
	private IDaoPersonne daoPersonneJpa;

	@Autowired
	private IDaoAdministrateur daoAdministrateurJpa;

	@Autowired
	private IDaoLecteur daoLecteurJpa;

	// pour la cohérence des resultats
	// TODO Ajouter une methode pour avant tous les tests clean la bdd

	@BeforeEach
	public void init() {
		daoPersonneJpa.deleteAll();
	}

	@Test
	public void testPersonnesAdminLecteur() {
		daoPersonneJpa.save(new Personne(null, "Jean", "Valjean", "jean@inetum.com", "0102030405",
					"rue de la maison"));
		daoPersonneJpa.save(new Personne(null, "Gerard", "Duchemin", "gerard@inetum.com", "0102030405",
					"rue de la maison"));
		daoAdministrateurJpa.save(new Administrateur(null, "Roger", "Theboss", "roger@biblio.com", null,
					null, "rogeradmin", "1234"));
		daoLecteurJpa.save(new Lecteur(null, "Benoit", "Georges", null, null, null));

		List<Personne> personnes = daoPersonneJpa.findAll();
		assertEquals(personnes.size(), 4);

		logger.trace("Liste de personnes=" + personnes);

		List<Administrateur> admins = daoAdministrateurJpa.findAll();
		assertEquals(admins.size(), 1);

		logger.trace("Liste d'administrateurs=" + admins);

		List<Lecteur> lecteurs = daoLecteurJpa.findAll();
		assertEquals(lecteurs.size(), 1);

		logger.trace("Liste de lecteurs=" + lecteurs);
	}

	@Test
	public void testPersonne() {
		Personne jean = new Personne(null, "Jean", "Valjean", "jean@inetum.com", "0102030405",
					"rue de la maison");
		Personne person = daoPersonneJpa.save(jean);
		var idJean = person.getIdPersonne();
		logger.trace("person=" + person);

		assertEquals(person.getIdPersonne(), idJean);
		assertEquals(person.getNom(), "Valjean");
		assertEquals(person.getPrenom(), "Jean");
		assertEquals(person.getEmail(), "jean@inetum.com");
		assertEquals(person.getAdresse(), "rue de la maison");
		assertEquals(person.getTelephone(), "0102030405");

		person.setPrenom("Jeannot");
		person.setNom("Valjeant");
		person.setAdresse("a la maison");
		person.setEmail("j.valjeant@inetum.com");
		person.setTelephone("0602030405");

		daoPersonneJpa.save(person);
		assertEquals(jean.getIdPersonne(), idJean);
		assertEquals(jean.getNom(), "Valjeant");
		assertEquals(jean.getPrenom(), "Jeannot");
		assertEquals(jean.getEmail(), "j.valjeant@inetum.com");
		assertEquals(jean.getAdresse(), "a la maison");
		assertEquals(jean.getTelephone(), "0602030405");

		logger.trace("personUpdate=" + person);

	}

	@Test
	public void testUpdatePersonne() {
		Personne person = daoPersonneJpa.save(new Personne(null, "Jean", "Valjean", "jean@inetum.com",
					"0102030405", "rue de la maison"));
		logger.trace("person=" + person);
		person.setPrenom("Jeannot");
		person.setNom("Valjeant");
		person.setEmail("j.valjeant@inetum.com");
		person.setTelephone("");
		person.setAdresse("30 rue du bonheur");

		daoPersonneJpa.save(person);
		assertTrue(person.getEmail().equals("j.valjeant@inetum.com"));
		logger.trace("personUpdate=" + person);

	}

	@Test
	public void testUpdateAdmin() {
		Administrateur admin = daoAdministrateurJpa.save(new Administrateur(null, "Roger", "Theboss",
					"roger@biblio.com", null, null, "rogeradmin", "1234"));
		logger.trace("admin=" + admin);
		admin.setNom("Robert");
		admin.setEmail("r.robert@biblio.com");
		admin.setUsername("rrobert");
		admin.setPassword("0101");

		daoAdministrateurJpa.save(admin);
		assertTrue(admin.getEmail().equals("r.robert@biblio.com"));
		assertTrue(admin.getUsername().equals("rrobert"));
		assertTrue(admin.getPassword().equals("0101"));
		logger.trace("adminUpdate=" + admin);

	}

	@Test
	public void testUpdateLecteur() {
		Lecteur lecteur = daoLecteurJpa.save(new Lecteur(null, "Benoit", "Georges", null, null, null));
		logger.trace("lecteur=" + lecteur);
		lecteur.setNom("Georges");
		lecteur.setEmail("b.georges@inetum.com");

		daoLecteurJpa.save(lecteur);
		assertTrue(lecteur.getEmail().equals("b.georges@inetum.com"));
		logger.trace("lecteurUpdate=" + lecteur);

	}

	@Test
	public void testDeletePersonne() {
		Personne person = daoPersonneJpa.save(new Personne(null, "Jean", "Valjean", "jean@inetum.com",
					"0102030405", "rue de la maison"));
		logger.trace("person=" + person);
		Long id = person.getIdPersonne();
		logger.trace("id of person=" + id);

		daoPersonneJpa.deleteById(id);
		Personne idDeleted = daoPersonneJpa.findById(id).orElse(null);
		assertNull(idDeleted);
		logger.trace("idDeleted=" + idDeleted);
	}

	@Test
	public void testDeleteAdmin() {
		Administrateur admin = daoAdministrateurJpa.save(new Administrateur(null, "Roger", "Theboss",
					"roger@biblio.com", null, null, "rogeradmin", "1234"));
		logger.trace("admin=" + admin);
		Long id = admin.getIdPersonne();
		logger.trace("id of admin=" + id);

		daoAdministrateurJpa.deleteById(id);
		Administrateur AdminDeleted = daoAdministrateurJpa.findById(id).orElse(null);
		assertNull(AdminDeleted);
		logger.trace("idDeleted=" + AdminDeleted);
	}

	@Test
	public void testDeleteLecteur() {
		Lecteur lecteur = daoLecteurJpa.save(new Lecteur(null, "Benoit", "Georges", null, null, null));
		logger.trace("lecteur=" + lecteur);
		Long id = lecteur.getIdPersonne();
		logger.trace("id of person=" + id);

		daoLecteurJpa.deleteById(id);
		Lecteur LecteuridDeleted = daoLecteurJpa.findById(id).orElse(null);
		assertNull(LecteuridDeleted);
		logger.trace("idDeleted=" + LecteuridDeleted);
	}

	@Test
	public void testRechercheByEmail() {
		Personne person = daoPersonneJpa.save(new Personne(null, "Jean", "Valjean", "jean@inetum.com",
					"0102030405", "rue de la maison"));
		logger.trace("person=" + person);
		Personne personToCheck = daoPersonneJpa.findByEmail("jean@inetum.com");
		assertTrue(person.getEmail().equals(personToCheck.getEmail()));
	}

	@Test
	public void testRechercheByNom() {
		Personne personne1 = daoPersonneJpa.save(new Personne(null, "Jean", "Valjean", "jean@inetum.com",
					"0102030405", "rue de la maison"));
		Personne personne2 = daoPersonneJpa.save(new Personne(null, "Gerard", "Valjean",
					"gerard@inetum.com", "0102030405", "rue de la maison"));

		List<Personne> personnesParNom = daoPersonneJpa.findByNom("Valjean");
		assertEquals(2, personnesParNom.size());
		assertTrue(personne1.getNom().equals(personne2.getNom()));
		assertFalse(personne1.equals(personne2));
	}

	@Test
	public void testRechercheByNomAndPnom() {
		Personne personne1 = daoPersonneJpa.save(new Personne(null, "Jean", "Valjean", "jean@inetum.com",
					"010000000", "rue du chateau"));
		Personne personne2 = daoPersonneJpa.save(new Personne(null, "Jean", "Valjean",
					"jean.v@inetum.com", "0102030405", "rue de la maison"));
		Personne personne3 = daoPersonneJpa.save(new Personne(null, "Maurice", "Valjean",
					"jean.v@inetum.com", "0102030405", "rue de la maison"));

		List<Personne> personnesParNomAndPrenom = daoPersonneJpa.findByNomAndPrenom("Valjean", "Jean");
		List<Personne> personnes = daoPersonneJpa.findAll();
		assertEquals(2, personnesParNomAndPrenom.size());
		assertNotEquals(personnes.size(), personnesParNomAndPrenom.size());
		assertTrue(personne1.getNom().equals(personne2.getNom()));
		assertFalse(personne1.equals(personne2));
	}

}
