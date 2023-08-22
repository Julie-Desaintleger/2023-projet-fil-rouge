package com.inetum.AppBibliotheque.personnes.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoPersonne;
import com.inetum.AppBibliotheque.personnes.entities.Personne;

@RestController
@RequestMapping(value = "/api-personnes/personne", headers = "Accept=application/json")
public class PersonneRestCtrl {

	// NB: cette version 1 n'utilise pas encore les DTOs

	@Autowired
	private IDaoPersonne daoPersonneJpa;

	// exemple URL de déclenchement: ./api-personnes/personne/1
	@GetMapping("/{idPersonne}")
	public ResponseEntity<?> getPersonneById(@PathVariable("idPersonne") Long idPersonne) {
		Personne personne = daoPersonneJpa.findById(idPersonne).orElse(null);
		if (personne != null) {
			return new ResponseEntity<Personne>(personne, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"personne not found\"}",
						HttpStatus.NOT_FOUND);
		}

	}

	// exemple de fin d'URL de déclenchement:
	// ./api-personnes/personne
	@GetMapping("")
	public List<Personne> getPersonnes() {
		return daoPersonneJpa.findAll();

	}

	// exemple de fin d'URL de déclenchement:
	// ./api-personnes/personne
	// appelé en mode POST avec dans la partie invisible "body" de la requete ;
	// { "idPersonne" : null, "prenom" : "Toto", "nom" : "Durand", "email" :
	// "toto@mail.com", "telephone" : "0102030405" , "adresse" : "chez moi"}
	@PostMapping("")
	public Personne postPersonne(@RequestBody Personne newPersonne) {
		Personne personneSaved = daoPersonneJpa.save(newPersonne);
		return personneSaved; // on retourne la personne avec la clé primaire auto-incr
	}

	// exemple de fin d'URL de déclenchement:
	// ./api-personnes/personne
	// appelé en mode PUT avec dans la partie invisible "body" de la requete ;
	// { "idPersonne" : null, "prenom" : "Toto", "nom" : "Durand", "email" :
	// "toto@mail.com", "telephone" : "0102030405" , "adresse" : "chez moi"}
	@PutMapping({ "", "/{idPersonne}" })
	public ResponseEntity<?> putPersonneToUpdate(@RequestBody Personne personne,
				@PathVariable(value = "idPersonne", required = false) Long idPersonne) {

		Long numPersonneToUpdate = idPersonne != null ? idPersonne : personne.getIdPersonne();

		Personne personneExistante = numPersonneToUpdate != null
					? daoPersonneJpa.findById(numPersonneToUpdate).orElse(null)
					: null;
		if (personneExistante == null) {
			return new ResponseEntity<String>("{ \"err\" : \"personne not found\"}",
						HttpStatus.NOT_FOUND); // 404
		} else {
			if (personne.getIdPersonne() == null) {
				personne.setIdPersonne(numPersonneToUpdate);
			}
			daoPersonneJpa.save(personne);
			return new ResponseEntity<Personne>(personne, HttpStatus.OK);
		}

	}

	// exemple URL de déclenchement: ./api-personnes/personne/1
	@DeleteMapping("/{idPersonne}")
	public ResponseEntity<?> deleteCompteById(@PathVariable("idPersonne") Long idPersonne) {
		Personne personneAsupprimer = daoPersonneJpa.findById(idPersonne).orElse(null);
		if (personneAsupprimer != null) {
			daoPersonneJpa.deleteById(idPersonne);
			return new ResponseEntity<String>("{ \"done\" : \"personne deleted\"}", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"personne not found\"}",
						HttpStatus.NOT_FOUND); // 404
		}

	}

}
