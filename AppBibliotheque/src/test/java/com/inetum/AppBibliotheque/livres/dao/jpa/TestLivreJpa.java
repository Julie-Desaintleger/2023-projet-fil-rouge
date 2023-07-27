package com.inetum.AppBibliotheque.livres.dao.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

		Domaine domaine1 = new Domaine(null, "Developpement", "les bases du développemet JAVA");
		daoDomaine.insert(domaine1);

		Exemplaire exemplaire1 = daoExemplaire.insert(new Exemplaire(null, "Bases JPA Hibernete", "Didier", "First",domaine1));
		exemplaire1.setDisponibilite(true);
		daoExemplaire.update(exemplaire1);
		
		Domaine domaine2 = new Domaine(null, "Back-end", "les bases d'un bon code");
		
		daoDomaine.insert(domaine2);
		
		Exemplaire exemplaire2 = daoExemplaire.insert(new Exemplaire(null, "Java", "Romain", "Oracle", domaine2));
		exemplaire2.setDisponibilite(false);
		exemplaire2.setEtat(Exemplaire.EtatLivre.ABIME);
		daoExemplaire.update(exemplaire2);
		
		Domaine domaine3 = new Domaine(null, "Front-end", "les bases d'une bonne interface graphique");
		daoDomaine.insert(domaine3);
		
		Exemplaire exemplaire3 = daoExemplaire.insert(new Exemplaire(null, "Bases HTML", "Anouar", "Eyrolles", domaine3));
		exemplaire3.setDisponibilite(false);
		exemplaire3.setEtat(Exemplaire.EtatLivre.HORS_SERVICE);
		daoExemplaire.update(exemplaire3);

		//FIND BY
		List<Domaine> DomaineRelu = daoDomaine.findDomaineByNom("Back-end");
		assertTrue(DomaineRelu.size() >= 1);

		logger.trace(" DomaineRelu=" + DomaineRelu);
		
		//DELETE

		daoDomaine.deleteById(domaine3.getId());
		Domaine domaine3Relu = daoDomaine.findById(domaine3.getId());
		assertNull(domaine3Relu);
		
		//UPDATE
		
		domaine2.setNom("On vient de l'effacer");
		daoDomaine.update(domaine2);
		assertEquals("On vient de l'effacer", domaine2.getNom());
		

//		livre7.setTitre("Java PHP");
//		daoLivreJpa.update(livre7);

//		assertEquals(6, daoLivreJpa.findAll().size());
//		System.err.println(daoLivreJpa.findAll().size());

	}

}
