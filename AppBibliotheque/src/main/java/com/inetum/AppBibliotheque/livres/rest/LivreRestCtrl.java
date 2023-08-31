package com.inetum.AppBibliotheque.livres.rest;

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

import com.inetum.AppBibliotheque.converter.GenericConverter;
import com.inetum.AppBibliotheque.livres.dto.DomaineDto;
import com.inetum.AppBibliotheque.livres.dto.ExemplaireDto;
import com.inetum.AppBibliotheque.livres.dto.ExemplaireDtoEx;
import com.inetum.AppBibliotheque.livres.dto.ExemplaireDtoEx2;
import com.inetum.AppBibliotheque.livres.dto.LivreDto;
import com.inetum.AppBibliotheque.livres.dto.LivreDtoEx;
import com.inetum.AppBibliotheque.livres.dto.LivreDtoEx2;
import com.inetum.AppBibliotheque.livres.entities.Domaine;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.livres.services.IServiceDomaine;
import com.inetum.AppBibliotheque.livres.services.IServiceExemplaire;
import com.inetum.AppBibliotheque.livres.services.IServiceLivre;

@RestController
@RequestMapping(value = "/api-livres/livre", headers = "Accept=application/json")

public class LivreRestCtrl {

	@Autowired
	private IServiceLivre serviceLivre;
	@Autowired
	private IServiceDomaine serviceDomaine;
	@Autowired
	private IServiceExemplaire serviceExemplaire;

	// -------LIVRE---------------------
	/********** FIND BY ID */

	// exemple URL de déclenchement: ./api-livres/livre/1
	@GetMapping("/{idLivre}")
	public ResponseEntity<?> getLivreById(@PathVariable("idLivre") Long idLivre) {
		LivreDto livreDto = serviceLivre.searchByIdWithDomaine(idLivre);
		if (livreDto != null) {
			return new ResponseEntity<LivreDto>(livreDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"livre not found\"}", HttpStatus.NOT_FOUND);
		}
	}

	/*********** FINDALL */
	@GetMapping("")
	public List<LivreDtoEx2> getLivres() {
		return serviceLivre.searchAlldWithAllDomaine();

	}

	/*********** SAVE */

	@PostMapping("")
	public LivreDto postLivre(@RequestBody LivreDtoEx nouveauLivre) {
		return serviceLivre.saveOrUpdateLivreDtoEx(nouveauLivre);
	}

	/********** UPDATE **/

	@PutMapping({ "", "/{idLivre}" })
	public ResponseEntity<?> putLivreToUpdate(@RequestBody LivreDtoEx livreDto,
			@PathVariable(value = "idLivre", required = false) Long idLivre) {
		Long idLivreToUpdate = idLivre != null ? idLivre : livreDto.getIdLivre();
		serviceLivre.shouldExistById(idLivreToUpdate); // remonte NotFoundException si pas trouvé
		if (livreDto.getIdLivre() == null)
			livreDto.setIdLivre(idLivreToUpdate);
		// on s'appuie ici sur la méthode spécifique ci dessous du serviceCompte
		serviceLivre.saveOrUpdateLivreDtoEx(livreDto);
		return new ResponseEntity<LivreDto>(livreDto, HttpStatus.OK);
	}

	/********** DELETE **/

	@DeleteMapping("/{idLivre}")
	public ResponseEntity<?> deleteLivreById(@PathVariable("idLivre") Long idLivre) {
		Livre LivreAsupprimer = serviceLivre.searchById(idLivre);
		if (LivreAsupprimer == null)
			return new ResponseEntity<String>("{ \"err\" : \"livre not found\"}", HttpStatus.NOT_FOUND); // NOT_FOUND
																											// code http
		else
			serviceLivre.deleteById(idLivre);
		return new ResponseEntity<String>("{ \"done\" : \"livre deleted\"}", HttpStatus.OK);
		// ou bien
		// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// --------EXEMPLAIRE----------------

	/************ FIND BY ID */

	@GetMapping("/exemplaire/{idExemp}")
	public ResponseEntity<?> getExempalireById(@PathVariable("idExemp") Long idExemp) {
		ExemplaireDto exemplaireDto = serviceExemplaire.searchByIdWithLivre(idExemp);
		if (exemplaireDto != null) {
			return new ResponseEntity<ExemplaireDto>(exemplaireDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"exemplaire not found\"}", HttpStatus.NOT_FOUND);
		}
	}

	// exemple de fin d'URL de déclenchement:
	// ./api-livres/livre

	/************** FIND ALL */

	@GetMapping("exemp")
	public List<ExemplaireDtoEx2> getExemplaire() {
		return serviceExemplaire.searchAllWithAllLivre();

	}

	/*************** SAVE */

	// exemple de fin d'URL: ./api-livres/livre/Exemplaire
	@PostMapping("/exemplaire")
	public ExemplaireDto postExemplaire(@RequestBody ExemplaireDtoEx nouvelExemplaire) {
		return serviceExemplaire.saveOrUpdateExemplaireDtoEx(nouvelExemplaire);
	}

	// exemple de fin d'URL: ./api-livres/livre/1
	// appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idLivre" : null , "titre" : " " , "auteur" : ,"editeur" : }

	/** UPDATE **/

	@PutMapping({ "/exemplaire", "/exemplaire/{idExemp}" })
	public ResponseEntity<?> putExemplaireToUpdate(@RequestBody ExemplaireDtoEx exemplaireDto,
			@PathVariable(value = "idExemp", required = false) Long idExemp) {

		Long idExempToUpdate = idExemp != null ? idExemp : exemplaireDto.getIdExemp();
		serviceLivre.shouldExistById(idExempToUpdate); // remonte NotFoundException si pas trouvé
		if (exemplaireDto.getIdExemp() == null)
			exemplaireDto.setIdExemp(idExempToUpdate);
		// on s'appuie ici sur la méthode spécifique ci dessous du serviceCompte
		serviceExemplaire.saveOrUpdateExemplaireDtoEx(exemplaireDto);
		return new ResponseEntity<ExemplaireDto>(exemplaireDto, HttpStatus.OK);
	}

	
	// ----------DOMAINE------
	/**************** SAVE **/

	// exemple de fin d'URL: ./api-livres/livre/domaine
	// appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idLivre" : null , "titre" : " " , "auteur" : ,"editeur" : }
	@PostMapping("/domaine")
	public DomaineDto postDomaine(@RequestBody DomaineDto nouveauDomaine) {
		Domaine domaineEnregistreEnBase = serviceDomaine
				.saveOrUpdate(GenericConverter.map(nouveauDomaine, Domaine.class));
		return GenericConverter.map(domaineEnregistreEnBase, DomaineDto.class);// on retourne le livre avec la clee
																				// primaire auro-incremenrée
	}

}
