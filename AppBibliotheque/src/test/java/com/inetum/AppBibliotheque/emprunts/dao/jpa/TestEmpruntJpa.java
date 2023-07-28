package com.inetum.AppBibliotheque.emprunts.dao.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inetum.AppBibliotheque.emprunts.dao.interfaces.IDaoEmprunt;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter.TypeEmprunt;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire.EtatLivre;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoLecteur;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;

@SpringBootTest
public class TestEmpruntJpa {

	Logger logger = LoggerFactory.getLogger(TestEmpruntJpa.class);

	@Autowired
	private IDaoEmprunt daoEmpruntJpa;

	@Autowired
	private IDaoLecteur daoLecteur;

	@Autowired
	private IDaoExemplaire daoExemplLivre;

	@Autowired
	private IDaoLivre daoLivre;

	@Test
	public void testEmprunt() {

		Livre livre = daoLivre.insert(new Livre(null, "le bon livre", "by me", "the other side", null));
		Livre livre2 = daoLivre
					.insert(new Livre(null, "the other livre", "by you", "the other side", null));
		Exemplaire livrePris = daoExemplLivre
					.insert(new Exemplaire(null, EtatLivre.BON_ETAT, "exmpl1", livre));
		Exemplaire livrePris1 = daoExemplLivre
					.insert(new Exemplaire(null, EtatLivre.ABIME, "exmpl1", livre2));
		Lecteur lecteur = daoLecteur.insert(new Lecteur(null, "Roger", "dupont", null, null, null));

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateDebut = format.parse("2009-12-02");
			Date dateFin = format.parse("2009-12-13");
			daoEmpruntJpa.insert(new Emprunter(null, dateDebut, dateFin, TypeEmprunt.RESERVE, lecteur,
						livrePris));
			daoEmpruntJpa.insert(new Emprunter(null, dateDebut, dateFin, TypeEmprunt.RESERVE, lecteur,
						livrePris1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			daoEmpruntJpa.insert(
						new Emprunter(null, null, null, TypeEmprunt.RESERVE, lecteur, livrePris));
		}

		List<Emprunter> emprunts = daoEmpruntJpa.findAll();
		assertEquals(emprunts.size(), 2);

		logger.trace("Liste d emprunts=" + emprunts);

	}

}
