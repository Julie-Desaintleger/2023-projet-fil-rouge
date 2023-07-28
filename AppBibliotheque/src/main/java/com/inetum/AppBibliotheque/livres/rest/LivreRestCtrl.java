package com.inetum.AppBibliotheque.livres.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.entities.Livre;

@RestController
@RequestMapping(value = "/api-livres/livre", headers = "Accept=application/json")
public class LivreRestCtrl {

	// NB: cette version 1 n'utilise pas encore les DTOs

	@Autowired
	private IDaoLivre daoLivreJpa;

	// exemple URL de déclenchement: ./api-livres/livre/1
	@GetMapping("/{idLivre}")

	public ResponseEntity<?> getLivreById(@PathVariable("idLivre") Long idLivre) {
		Livre livre = daoLivreJpa.findById(idLivre);
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

}
