package com.inetum.AppBibliotheque.livres.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoDomaine;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.entities.Domaine;
import com.inetum.AppBibliotheque.livres.entities.Livre;

@RestController
@RequestMapping(value = "/api-livres/livre", headers = "Accept=application/json")
public class LivreRestCtrl {

	// NB: cette version 1 n'utilise pas encore les DTOs

	@Autowired
	private IDaoLivre daoLivreJpa;
	
	@Autowired
	private IDaoDomaine daoDomaineJpa;

	@Autowired
	private IDaoExemplaire daoExemplaireJpa;

	// exemple URL de déclenchement: ./api-livres/livre/1
	@GetMapping("/{idLivre}")

	public ResponseEntity<?> getLivreById(@PathVariable("idLivre") Long idLivre) {
		Livre livre = daoLivreJpa.findById(idLivre).orElse(null);
		if (livre != null) {
			return new ResponseEntity<Livre>(livre, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"livre not found\"}", HttpStatus.NOT_FOUND);
		}
	}

	// exemple de fin d'URL de déclenchement:
	// ./api-livres/livre

	@GetMapping("")
	public List<Livre> getLivres() {
		return daoLivreJpa.findAll();

	}

	// DELETE

	@DeleteMapping("/{idLivre}")
	public ResponseEntity<?> deleteLivreById(@PathVariable("idLivre") Long idLivre) {
		Livre LivreAsupprimer = daoLivreJpa.findById(idLivre).orElse(null);
		if (LivreAsupprimer == null)
			return new ResponseEntity<String>("{ \"err\" : \"livre not found\"}", HttpStatus.NOT_FOUND); // NOT_FOUND =
																											// code http
																											// 404
		else
			daoLivreJpa.deleteById(idLivre);
		return new ResponseEntity<String>("{ \"done\" : \"livre deleted\"}", HttpStatus.OK);
		// ou bien
		// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// INSERT

	// exemple de fin d'URL: ./api-livres/livre/domaine
	// appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idLivre" : null , "titre" : " " , "auteur" : ,"editeur" : }
	@PostMapping("/domaine")
	public Domaine postDomaine( @RequestBody  Domaine nouveauDomaine) {
		Domaine domaineEnregistreEnBase = daoDomaineJpa.save(nouveauDomaine);
		return domaineEnregistreEnBase;// on retourne le livre avec la clee primaire auro-incremenrée
	}


	@PostMapping("")
	public Livre postLivre(@RequestBody Livre nouveauLivre) {		
		Livre livreEnregistreEnBase = daoLivreJpa.save(nouveauLivre);
		return livreEnregistreEnBase;// on retourne le livre avec la clee primaire auro-incremenrée
	}
}
