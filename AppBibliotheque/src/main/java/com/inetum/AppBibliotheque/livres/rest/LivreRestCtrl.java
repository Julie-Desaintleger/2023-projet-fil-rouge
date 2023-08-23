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
import com.inetum.AppBibliotheque.livres.Dto.DomaineDto;
import com.inetum.AppBibliotheque.livres.Dto.LivreDto;
import com.inetum.AppBibliotheque.livres.Dto.LivreDtoEx;
import com.inetum.AppBibliotheque.livres.entities.Domaine;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.livres.services.IServiceDomaine;
import com.inetum.AppBibliotheque.livres.services.IServiceLivre;

@RestController
@RequestMapping(value = "/api-livres/livre", headers = "Accept=application/json")

public class LivreRestCtrl {

	// NB: cette version 1 n'utilise pas encore les DTOs

	@Autowired
	private IServiceLivre serviceLivre;
	
	@Autowired
	private IServiceDomaine servieDomaine;

	

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

	// exemple de fin d'URL de déclenchement:
	// ./api-livres/livre

	@GetMapping("")
	public List<LivreDto> getLivres() {
		return serviceLivre.searchAllDto();

	}

	// DELETE

	@DeleteMapping("/{idLivre}")
	public ResponseEntity<?> deleteLivreById(@PathVariable("idLivre") Long idLivre) {
		Livre LivreAsupprimer = serviceLivre.searchById(idLivre);
		if (LivreAsupprimer == null)
			return new ResponseEntity<String>("{ \"err\" : \"livre not found\"}", HttpStatus.NOT_FOUND); // NOT_FOUND =
																											// code http
																											// 404
		else
			serviceLivre.deleteById(idLivre);
		return new ResponseEntity<String>("{ \"done\" : \"livre deleted\"}", HttpStatus.OK);
		// ou bien
		// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// INSERT

	// exemple de fin d'URL: ./api-livres/livre/domaine
	// appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idLivre" : null , "titre" : " " , "auteur" : ,"editeur" : }
	@PostMapping("/domaine")
	public DomaineDto postDomaine( @RequestBody  DomaineDto nouveauDomaine) {
		Domaine domaineEnregistreEnBase = servieDomaine.saveOrUpdate(GenericConverter.map(nouveauDomaine, Domaine.class));
		return GenericConverter.map(domaineEnregistreEnBase, DomaineDto.class) ;// on retourne le livre avec la clee primaire auro-incremenrée
	}


	@PostMapping("")
	public LivreDto postLivre(@RequestBody LivreDtoEx nouveauLivre) {		
		return serviceLivre.saveOrUpdateLivreDtoEx(nouveauLivre);
	}
	
	// exemple de fin d'URL: ./api-livres/livre/1
		// appelé en mode POST avec dans la partie invisible "body" de la requête:
		// { "idLivre" : null , "titre" : " " , "auteur" : ,"editeur" : }
	
			@PutMapping({"" , "/{idLivre}" }) 
			public ResponseEntity<?> putLivreToUpdate(@RequestBody LivreDtoEx livreDto , 
					      @PathVariable(value="idLivre",required = false ) Long idLivre) {
				
				    Long idLivreToUpdate = idLivre!=null ? idLivre :livreDto.getIdLivre();
				   
				    serviceLivre.shouldExistById(idLivreToUpdate); //remonte NotFoundException si pas trouvé
				    
				    if(livreDto.getIdLivre()==null)
				    	livreDto.setIdLivre(idLivreToUpdate);
				    
				    //on s'appuie ici sur la méthode spécifique ci dessous du serviceCompte
					serviceLivre.saveOrUpdateLivreDtoEx(livreDto); 
					
					return new ResponseEntity<LivreDto>(livreDto , HttpStatus.OK);
			}	
}
