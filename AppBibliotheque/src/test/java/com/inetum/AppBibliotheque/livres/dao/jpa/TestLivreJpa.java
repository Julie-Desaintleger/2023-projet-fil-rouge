package com.inetum.AppBibliotheque.livres.dao.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoDomaine;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.entities.Domaine;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Livre;

@SpringBootTest // classe interprétée par JUnit et SpringBoot
public class TestLivreJpa {

	Logger logger = LoggerFactory.getLogger(TestLivreJpa.class);

	@Autowired // pour initialisation daoCompte
	// qui va référencer un composant Spring existant compatible
	// avec l'interface DaoCompte (DaoCompteJpa avec@Repository)
	private IDaoLivre daoLivreJpa;

	@Autowired
	private IDaoExemplaire daoExemplaire;

	@Autowired
	private IDaoDomaine daoDomaine;

	@Test
	public void testQueries() {
		
		// LIVRE1 

		Domaine domaine1 = new Domaine(null, "Developpement", "les bases du développemet JAVA");
		daoDomaine.insert(domaine1);

		Livre livre1 = daoLivreJpa.insert(new Livre(null, "PHP", "Victor", "Eni", 3, domaine1));
		
		// NB EXEMPLAIRE(n,p) = EXEMPLAIRE p du LIVRE n
		
		Exemplaire exemplaire11 = daoExemplaire
				.insert(new Exemplaire(null, Exemplaire.EtatLivre.ABIME, "exemlpaire11", livre1));
		exemplaire11.setIsDisponibilite(true);
		daoExemplaire.update(exemplaire11);

		Exemplaire exemplaire12 = daoExemplaire
				.insert(new Exemplaire(null, Exemplaire.EtatLivre.BON_ETAT, "exemlpaire12", livre1));
		exemplaire12.setIsDisponibilite(true);
		daoExemplaire.update(exemplaire12);

		Exemplaire exemplaire13 = daoExemplaire
				.insert(new Exemplaire(null, Exemplaire.EtatLivre.HORS_SERVICE, "exemlpaire13", livre1));
		exemplaire13.setIsDisponibilite(false);
		daoExemplaire.update(exemplaire13);

		Domaine domaine2 = new Domaine(null, "Back-end", "les bases d'un bon code");
		daoDomaine.insert(domaine2);

		Livre livre2 = daoLivreJpa.insert(new Livre(null, "Java", "Romain", "Oracle", 2, domaine2));
		Exemplaire exemplaire21 = daoExemplaire
				.insert(new Exemplaire(null, Exemplaire.EtatLivre.BON_ETAT, "exemlpaire21", livre2));
		exemplaire21.setIsDisponibilite(true);
		daoExemplaire.update(exemplaire21);

		Exemplaire exemplaire22 = daoExemplaire
				.insert(new Exemplaire(null, Exemplaire.EtatLivre.HORS_SERVICE, "exemlpaire22", livre2));
		exemplaire22.setIsDisponibilite(false);
		daoExemplaire.update(exemplaire22);

		
		

//		Domaine domaine3 = new Domaine(null, "Front-end", "les bases d'une bonne interface graphique");
//		daoDomaine.insert(domaine3);
//
//		Exemplaire exemplaire3 = daoExemplaire
//				.insert(new Exemplaire(null, "Bases HTML", "Anouar", "Eyrolles", domaine3));
//		exemplaire3.setDisponibilite(false);
//		exemplaire3.setEtat(Exemplaire.EtatLivre.HORS_SERVICE);
//		daoExemplaire.update(exemplaire3);

		
		
		// FIND BY (LIVRE)
		
		List<Livre> LivreRelu = daoLivreJpa.findLivreByTitre("PHP");
		assertTrue(LivreRelu.size() >= 1);
		
		logger.trace(" LivreRelu=" + LivreRelu);
		
		// FIND BY (DOMAINE)
		
		List<Domaine> DomaineRelu = daoDomaine.findDomaineByNom("Developpement");
		assertTrue(DomaineRelu.size() >= 1);

		logger.trace(" DomaineRelu=" + DomaineRelu);

		// DELETE (EXEMPLAIRE)

		daoExemplaire.deleteById(exemplaire13.getIdExemp());
		Exemplaire exemplaire13Relu = daoExemplaire.findById(exemplaire13.getIdExemp());
		assertNull(exemplaire13Relu);

		// UPDATE (LIVRE)

		livre2.setAuteur("Julie");
		daoLivreJpa.update(livre2);
		assertEquals("Julie", livre2.getAuteur());
		
		// DISPONIBILITE
		
		Integer nbExempDispo = daoExemplaire.getDisponibilite(Exemplaire.EtatLivre.HORS_SERVICE).size();
		assertEquals(3, nbExempDispo);
		logger.trace(" nbExempDispo=" + nbExempDispo);
		
			
	}
	

}
