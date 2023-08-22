package com.inetum.AppBibliotheque.livres.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoDomaine;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.entities.Domaine;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Livre;

/**
 * classe utilitaire qui initialise un jeu de données au démarrage de
 * l'applicaion uile si developpement en mode -auto=create-drop
 */

@Component
@Profile("init")

public class InitDataSet {

	@Autowired
	private IDaoLivre daoLivreJpa;
	@Autowired
	private IDaoExemplaire daoExemplaire;

	@Autowired
	private IDaoDomaine daoDomaine;
	
	

	@PostConstruct
	public void initData() {
//		daoLivreJpa.save(new Livre(null,"PHP" , "Victor", "Eni"));
//    	daoLivreJpa.save(new Livre(null,"Java" , "Romain", "Oracle"));
//    	daoLivreJpa.save(new Livre(null,"Bases HTML" , "Anouar", "Eyrolles"));
//    	daoLivreJpa.save(new Livre(null,"Bases HTML" , "Emily", "Eyrolles"));
//    	daoLivreJpa.save(new Livre(null,"Bases CSS" , "Victor", "Fist"));
//    	daoLivreJpa.save(new Livre(null,"Bases JPA Hibernete" , "Didier", "m2i"));
//    	daoLivreJpa.save(new Livre(null,"Bases JPA Hibernete" , "Didier", "First"));
		
		// LIVRE1

		Domaine domaine1 = new Domaine(null, "Developpement", "les bases du développemet JAVA");
		daoDomaine.save(domaine1);

		Livre livre1 = daoLivreJpa.save(new Livre(null, "PHP", "Victor", "Eni", domaine1));

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

		Livre livre2 = daoLivreJpa.save(new Livre(null, "Java", "Romain", "Oracle", domaine2));
		Exemplaire exemplaire21 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.BON_ETAT, "exemlpaire21", livre2));
		exemplaire21.setIsDisponibilite(true);
		daoExemplaire.save(exemplaire21);

		Exemplaire exemplaire22 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.HORS_SERVICE, "exemlpaire22", livre2));
		exemplaire22.setIsDisponibilite(false);
		daoExemplaire.save(exemplaire22);

	}

}
