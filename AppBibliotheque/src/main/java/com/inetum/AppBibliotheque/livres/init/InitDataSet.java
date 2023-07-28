package com.inetum.AppBibliotheque.livres.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;

/**
 * classe utilitaire qui initialise un jeu de données au démarrage de
 * l'applicaion uile si developpement en mode -auto=create-drop
 */

@Component

public class InitDataSet {

	@Autowired
	private IDaoLivre daoLivreJpa;

	@PostConstruct
	public void initData() {
//		daoLivreJpa.insert(new Livre(null,"PHP" , "Victor", "Eni"));
//    	daoLivreJpa.insert(new Livre(null,"Java" , "Romain", "Oracle"));
//    	daoLivreJpa.insert(new Livre(null,"Bases HTML" , "Anouar", "Eyrolles"));
//    	daoLivreJpa.insert(new Livre(null,"Bases HTML" , "Emily", "Eyrolles"));
//    	daoLivreJpa.insert(new Livre(null,"Bases CSS" , "Victor", "Fist"));
//    	daoLivreJpa.insert(new Livre(null,"Bases JPA Hibernete" , "Didier", "m2i"));
//    	daoLivreJpa.insert(new Livre(null,"Bases JPA Hibernete" , "Didier", "First"));

	}

}
