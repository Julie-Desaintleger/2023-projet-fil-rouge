package com.inetum.AppBibliotheque.livres.init;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.inetum.AppBibliotheque.emprunts.dao.interfaces.IDaoEmprunt;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter.TypeEmprunt;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoDomaine;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.entities.Domaine;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire.EtatLivre;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoLecteur;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;

/**
 * classe utilitaire qui initialise un jeu de données au démarrage de
 * l'applicaion uile si developpement en mode -auto=create-drop
 */

@Component
@Profile("init")

public class InitDataSet {

	@Autowired
	private IDaoLivre daoLivre;
	@Autowired
	private IDaoExemplaire daoExemplaire;

	@Autowired
	private IDaoDomaine daoDomaine;
	@Autowired
	private IDaoLecteur daoLecteur;
	@Autowired
	//private IDaoEmprunter daoemprunter;
	private IDaoEmprunt daoEmpruntJpa;
	
	

	@PostConstruct
	public void initData() {
//		daoLivre.save(new Livre(null,"PHP" , "Victor", "Eni"));
//    	daoLivre.save(new Livre(null,"Java" , "Romain", "Oracle"));
//    	daoLivre.save(new Livre(null,"Bases HTML" , "Anouar", "Eyrolles"));
//    	daoLivre.save(new Livre(null,"Bases HTML" , "Emily", "Eyrolles"));
//    	daoLivre.save(new Livre(null,"Bases CSS" , "Victor", "Fist"));
//    	daoLivre.save(new Livre(null,"Bases JPA Hibernete" , "Didier", "m2i"));
//    	daoLivre.save(new Livre(null,"Bases JPA Hibernete" , "Didier", "First"));
		
		// LIVRE1

		Domaine domaine1 = new Domaine(null, "Developpement", "les bases du développemet JAVA");
		daoDomaine.save(domaine1);

		Livre livre1 = daoLivre.save(new Livre(null, "PHP", "Victor", "Eni", domaine1));

		// NB EXEMPLAIRE(n,p) = EXEMPLAIRE p du LIVRE n

		Exemplaire exemplaire11 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.ABIME, "exemlpaire11", livre1));
		exemplaire11.setIsDisponibilite(true);
		daoExemplaire.save(exemplaire11);

		Exemplaire exemplaire12 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.BON_ETAT, "exemlpaire12", livre1));
		exemplaire12.setIsDisponibilite(true);
		daoExemplaire.save(exemplaire12);

		Exemplaire exemplaire13 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.HORS_SERVICE, "exemlpaire13", livre1));
		exemplaire13.setIsDisponibilite(false);
		daoExemplaire.save(exemplaire13);

		Domaine domaine2 = new Domaine(null, "Back-end", "les bases d'un bon code");
		daoDomaine.save(domaine2);

		Livre livre2 = daoLivre.save(new Livre(null, "Java", "Romain", "Oracle", domaine2));
		Exemplaire exemplaire21 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.BON_ETAT, "exemlpaire21", livre2));
		exemplaire21.setIsDisponibilite(true);
		daoExemplaire.save(exemplaire21);

		Exemplaire exemplaire22 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.HORS_SERVICE, "exemlpaire22", livre2));
		exemplaire22.setIsDisponibilite(false);
		daoExemplaire.save(exemplaire22);
		
		
		Livre livre3 = daoLivre.save(new Livre(null, "the other livre", "by you", "the other side", domaine2));
		Exemplaire exemplaire31 = daoExemplaire.save(new Exemplaire(null, EtatLivre.BON_ETAT, "exemplaire31", livre3));
		Exemplaire exemplaire32 = daoExemplaire.save(new Exemplaire(null, EtatLivre.ABIME, "exemplaire32", livre3));
		Lecteur lecteur1 = daoLecteur.save(new Lecteur(null, "Roger", "dupont", null, null, null));

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateDebut = format.parse("2009-12-02");
			Date dateFin = format.parse("2009-12-13");
			daoEmpruntJpa.save(new Emprunter(null, dateDebut, dateFin, TypeEmprunt.RESERVE, lecteur1,
					exemplaire31));
			daoEmpruntJpa.save(new Emprunter(null, dateDebut, dateFin, TypeEmprunt.RESERVE, lecteur1,
					exemplaire32));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
			daoEmpruntJpa.save(
						new Emprunter(null, null, null, TypeEmprunt.RESERVE, lecteur1, exemplaire31));
		}

		Lecteur lecteur2 = daoLecteur.save(new Lecteur(null, "Benoit", "Georges", null, null, null));
		lecteur2.setNom("Georges");
		lecteur2.setEmail("b.georges@inetum.com");
	}
	
	


}
