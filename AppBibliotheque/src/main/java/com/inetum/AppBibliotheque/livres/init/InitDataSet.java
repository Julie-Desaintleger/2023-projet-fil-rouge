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
	// private IDaoEmprunter daoemprunter;
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

		Domaine domaine3 = new Domaine(null, "Front-en", "la clé pour avoir un bon visuel");
		daoDomaine.save(domaine3);

		Livre livre3 = daoLivre.save(new Livre(null, "Bases HTML", "Emily", "Eyrolles", domaine3));
		Exemplaire exemplaire31 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.BON_ETAT, "exemlpaire31", livre3));
		exemplaire31.setIsDisponibilite(true);
		daoExemplaire.save(exemplaire31);

		Exemplaire exemplaire32 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.HORS_SERVICE, "exemlpaire32", livre3));
		exemplaire32.setIsDisponibilite(false);
		daoExemplaire.save(exemplaire32);

		Livre livre4 = daoLivre.save(new Livre(null, "Bases JPA Hibernete", "Didier", "First", domaine1));

		Exemplaire exemplaire41 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.BON_ETAT, "exemlpaire41", livre4));
		exemplaire41.setIsDisponibilite(true);
		daoExemplaire.save(exemplaire41);

		Exemplaire exemplaire42 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.HORS_SERVICE, "exemlpaire42", livre4));
		exemplaire42.setIsDisponibilite(false);
		daoExemplaire.save(exemplaire42);

		Exemplaire exemplaire43 = daoExemplaire
				.save(new Exemplaire(null, Exemplaire.EtatLivre.ABIME, "exemlpaire43", livre4));
		exemplaire42.setIsDisponibilite(true);
		daoExemplaire.save(exemplaire43);

		Domaine domaine4 = new Domaine(null, "Roman", "l'art pour l'art et l'art pour le progrès");
		daoDomaine.save(domaine4);

		Livre livre5 = daoLivre
				.save(new Livre(null, "Le tour du monde en 80 jours", "Paul et Virginie", "Harmathan", domaine4));
		Livre livre6 = daoLivre.save(new Livre(null, "Les misérables", "Victor Hugo", "Harmathan", domaine4));
		Livre livre7 = daoLivre.save(new Livre(null, "Père Goriot", "Eugène Rastignac", "Harmathan", domaine4));
		Livre livre8 = daoLivre.save(new Livre(null, "Les trois mousquetaires", "Artagnan", "Harmathan", domaine4));
		Livre livre9 = daoLivre.save(new Livre(null, "La joie de vivre", "Zola", "Harmathan", domaine4));
		Livre livre10 = daoLivre
				.save(new Livre(null, "L'Enfance d'un chef", "Jean Paul Sartre", "Harmathan", domaine4));

		Exemplaire exemplaire51 = daoExemplaire.save(new Exemplaire(null, EtatLivre.BON_ETAT, "exemplaire51", livre5));
		exemplaire51.setIsDisponibilite(true);
		daoExemplaire.save(exemplaire51);
		
		Exemplaire exemplaire52 = daoExemplaire.save(new Exemplaire(null, EtatLivre.ABIME, "exemplaire52", livre5));
		exemplaire52.setIsDisponibilite(true);
		daoExemplaire.save(exemplaire52);
		Lecteur lecteur1 = daoLecteur
				.save(new Lecteur(null, "Roger", "dupont", "dupont@inetum.fr", "0754865211", "paris"));
		Lecteur lecteur2 = daoLecteur
				.save(new Lecteur(null, "Julie", "DesaintLeger", "julie@inetum.fr", "0754865211", "seine saint denis"));
		Lecteur lecteur3 = daoLecteur
				.save(new Lecteur(null, "Victor", "Sicard", "victor@inetum.fr", "0754865211", "Antony"));
		Lecteur lecteur4 = daoLecteur
				.save(new Lecteur(null, "Raphael", "Farina", "raphael@inetum.fr", "0754865211", "Sarcelle"));
		Lecteur lecteur5 = daoLecteur
				.save(new Lecteur(null, "Thibaud", "Glutron", "thibaud@inetum.fr", "0754865211", "Antony"));
		Lecteur lecteur6 = daoLecteur
				.save(new Lecteur(null, "Frederic", "Ros", "frederic@inetum.fr", "0754865211", "seine saint denis"));
		Lecteur lecteur7 = daoLecteur
				.save(new Lecteur(null, "Simon", "Granier", "simon@inetum.fr", "0754865211", "Fontanay aux roses"));
		Lecteur lecteur8 = daoLecteur
				.save(new Lecteur(null, "Roland", "Panzou", "roland@inetum.fr", "0754865211", "ile-de-france"));
		Lecteur lecteur9 = daoLecteur
				.save(new Lecteur(null, "Soulef", "Saoud", "soulef@inetum.fr", "0754865211", "ile-de-france"));
		Lecteur lecteur10 = daoLecteur
				.save(new Lecteur(null, "Anouar", "El Idrissi", "anouar@inetum.fr", "0754865211", "cergy pontoise"));

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateDebut = format.parse("2023-08-02");
			Date dateFin = format.parse("2023-08-13");
			daoEmpruntJpa.save(new Emprunter(null, dateDebut, dateFin, TypeEmprunt.RESERVE, lecteur1, exemplaire31));
			daoEmpruntJpa.save(new Emprunter(null, dateDebut, dateFin, TypeEmprunt.RESERVE, lecteur1, exemplaire32));

			daoEmpruntJpa.save(new Emprunter(null, dateDebut, dateFin, TypeEmprunt.RESERVE, lecteur1, exemplaire22));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.RESERVE, lecteur1, exemplaire52));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.RESERVE, lecteur1, exemplaire42));
			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.EFFECTIF, lecteur1, exemplaire12));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.RESERVE, lecteur1, exemplaire41));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.RESERVE, lecteur10, exemplaire42));
			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.EFFECTIF, lecteur10, exemplaire12));
			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.RESERVE, lecteur10, exemplaire52));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.RESERVE, lecteur3, exemplaire42));
			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.EFFECTIF, lecteur3, exemplaire12));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-09-26"),
					TypeEmprunt.RESERVE, lecteur3, exemplaire52));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.RESERVE, lecteur6, exemplaire13));
			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.EFFECTIF, lecteur6, exemplaire32));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.EFFECTIF, lecteur7, exemplaire52));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.RESERVE, lecteur7, exemplaire42));
			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.EFFECTIF, lecteur7, exemplaire12));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.EFFECTIF, lecteur8, exemplaire52));

			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.RESERVE, lecteur8, exemplaire42));
			daoEmpruntJpa.save(new Emprunter(null, format.parse("2023-08-13"), format.parse("2023-08-23"),
					TypeEmprunt.EFFECTIF, lecteur8, exemplaire12));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			daoEmpruntJpa.save(new Emprunter(null, null, null, TypeEmprunt.RESERVE, lecteur1, exemplaire31));
		}

		Lecteur lecteur11 = daoLecteur.save(new Lecteur(null, "Benoit", "Georges", null, null, null));
		lecteur11.setNom("Georges");
		lecteur11.setEmail("georges@inetum.fr");
	}

}
