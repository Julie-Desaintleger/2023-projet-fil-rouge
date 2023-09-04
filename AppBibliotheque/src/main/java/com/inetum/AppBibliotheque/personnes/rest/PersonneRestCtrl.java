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

import com.inetum.AppBibliotheque.personnes.dto.LecteurDto;
import com.inetum.AppBibliotheque.personnes.dto.PersonneDto;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;
import com.inetum.AppBibliotheque.personnes.entities.Personne;
import com.inetum.AppBibliotheque.personnes.services.IServiceLecteur;
import com.inetum.AppBibliotheque.personnes.services.IServicePersonne;

@RestController
@RequestMapping(value = "/api-personnes", headers = "Accept=application/json")
public class PersonneRestCtrl {

	@Autowired
	private IServicePersonne servicePersonne;

	@Autowired
	private IServiceLecteur serviceLecteur;

	// exemple URL de déclenchement: ./api-personnes/personne/1
	@GetMapping("/personne/{idPersonne}")
	public ResponseEntity<?> getPersonneById(@PathVariable("idPersonne") Long idPersonne) {
		PersonneDto personneDto = servicePersonne.searchDtoById(idPersonne);
		if (personneDto != null) {
			return new ResponseEntity<PersonneDto>(personneDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"personne not found\"}",
						HttpStatus.NOT_FOUND);
		}

	}

	// exemple de fin d'URL de déclenchement:
	// ./api-personnes/personne
	@GetMapping("/personne")
	public List<PersonneDto> getPersonnes() {
		return servicePersonne.searchAllDto();

	}

	// exemple de fin d'URL de déclenchement:
	// ./api-personnes/personne
	// appelé en mode POST avec dans la partie invisible "body" de la requete ;
	// { "idPersonne" : null, "prenom" : "Toto", "nom" : "Durand", "email" :
	// "toto@mail.com", "telephone" : "0102030405" , "adresse" : "chez moi"}
	@PostMapping("/personne")
	public PersonneDto postPersonne(@RequestBody PersonneDto newPersonne) {
		return servicePersonne.saveOrUpdateDto(newPersonne);
	}

	// exemple de fin d'URL de déclenchement:
	// ./api-personnes/personne
	// appelé en mode PUT avec dans la partie invisible "body" de la requete ;
	// { "idPersonne" : null, "prenom" : "Toto", "nom" : "Durand", "email" :
	// "toto@mail.com", "telephone" : "0102030405" , "adresse" : "chez moi"}
	@PutMapping({ "/personne", "/personne/{idPersonne}" })
	public ResponseEntity<?> putPersonneToUpdate(@RequestBody PersonneDto personne,
				@PathVariable(value = "idPersonne", required = false) Long idPersonne) {

		Long numPersonneToUpdate = idPersonne != null ? idPersonne : personne.getIdPersonne();

		PersonneDto personneExistante = numPersonneToUpdate != null
					? servicePersonne.searchDtoById(numPersonneToUpdate)
					: null;
		if (personneExistante == null) {
			return new ResponseEntity<String>("{ \"err\" : \"personne not found\"}",
						HttpStatus.NOT_FOUND); // 404
		} else {
			if (personne.getIdPersonne() == null) {
				personne.setIdPersonne(numPersonneToUpdate);
			}
			servicePersonne.saveOrUpdateDto(personne);
			return new ResponseEntity<PersonneDto>(personne, HttpStatus.OK);
		}

	}

	// exemple URL de déclenchement: ./api-personnes/personne/1
	@DeleteMapping("/personne/{idPersonne}")
	public ResponseEntity<?> deleteCompteById(@PathVariable("idPersonne") Long idPersonne) {
		Personne personneAsupprimer = servicePersonne.searchById(idPersonne);
		if (personneAsupprimer != null) {
			servicePersonne.deleteById(idPersonne);
			return new ResponseEntity<String>("{ \"done\" : \"personne deleted\"}", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"personne not found\"}",
						HttpStatus.NOT_FOUND); // 404
		}

	}

	// exemple URL de déclenchement: ./api-personnes/lecteur/1
	@GetMapping("/lecteur/{idPersonne}")
	public ResponseEntity<?> getLecteurById(@PathVariable("idPersonne") Long idPersonne) {
//			PersonneDto personneDto = servicePersonne.searchDtoById(idPersonne);
		LecteurDto lecteurDto = serviceLecteur.searchDtoById(idPersonne);
		if (lecteurDto != null) {
			return new ResponseEntity<LecteurDto>(lecteurDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"lecteur not found\"}",
						HttpStatus.NOT_FOUND);
		}

	}

	// exemple de fin d'URL de déclenchement:
	// ./api-personnes/lecteur
	@GetMapping("/lecteur")
	public List<LecteurDto> getLecteurs() {
		return serviceLecteur.searchAllDto();

	}

	// exemple de fin d'URL de déclenchement:
	// ./api-personnes/lecteur
	// appelé en mode POST avec dans la partie invisible "body" de la requete ;
	// { "idPersonne" : null, "prenom" : "Toto", "nom" : "Durand", "email" :
	// "toto@mail.com", "telephone" : "0102030405" , "adresse" : "chez moi"}
	@PostMapping("/lecteur")
	public PersonneDto postLecteur(@RequestBody LecteurDto newLecteur) {
		return serviceLecteur.saveOrUpdateDto(newLecteur);
	}

	// exemple de fin d'URL de déclenchement:
	// ./api-personnes/lecteur
	// appelé en mode PUT avec dans la partie invisible "body" de la requete ;
	// { "idPersonne" : null, "prenom" : "Toto", "nom" : "Durand", "email" :
	// "toto@mail.com", "telephone" : "0102030405" , "adresse" : "chez moi"}
	@PutMapping({ "/lecteur", "/lecteur/{idPersonne}" })
	public ResponseEntity<?> putLecteurToUpdate(@RequestBody LecteurDto lecteur,
				@PathVariable(value = "idPersonne", required = false) Long idPersonne) {

		Long numLecteurToUpdate = idPersonne != null ? idPersonne : lecteur.getIdPersonne();

		LecteurDto lecteurExistant = numLecteurToUpdate != null
					? serviceLecteur.searchDtoById(numLecteurToUpdate)
					: null;
		if (lecteurExistant == null) {
			return new ResponseEntity<String>("{ \"err\" : \"lecteur not found\"}",
						HttpStatus.NOT_FOUND); // 404
		} else {
			if (lecteur.getIdPersonne() == null) {
				lecteur.setIdPersonne(numLecteurToUpdate);
			}
			serviceLecteur.saveOrUpdateDto(lecteur);
			return new ResponseEntity<PersonneDto>(lecteur, HttpStatus.OK);
		}

	}

	// exemple URL de déclenchement: ./api-personnes/personne/1
	@DeleteMapping("/lecteur/{idPersonne}")
	public ResponseEntity<?> deleteLecteurById(@PathVariable("idPersonne") Long idPersonne) {
		Lecteur lecteurAsupprimer = serviceLecteur.searchById(idPersonne);
		if (lecteurAsupprimer != null) {
			serviceLecteur.deleteById(idPersonne);
			return new ResponseEntity<String>("{ \"done\" : \"lecteur deleted\"}", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"lecteur not found\"}",
						HttpStatus.NOT_FOUND); // 404
		}

	}

}
