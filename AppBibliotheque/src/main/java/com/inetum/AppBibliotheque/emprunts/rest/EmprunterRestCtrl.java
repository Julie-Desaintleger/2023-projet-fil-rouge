package com.inetum.AppBibliotheque.emprunts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDto;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDtoEx;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDtoEx2;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;
import com.inetum.AppBibliotheque.emprunts.services.IServiceEmprunter;

@RestController
@RequestMapping(value = "/api-emprunts/emprunt", headers = "Accept=application/json")
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class EmprunterRestCtrl {

	@Autowired
	private IServiceEmprunter serviceEmprunter;
	

	/********** FIND BY ID */

	// exemple URL de déclenchement: ./api-emprunts/emprunt/1
	@GetMapping("/{idEmprunt}")
	public ResponseEntity<?> getEmpruntById(@PathVariable("idEmprunt") Long idEmprunt) {
		EmprunterDto emprunterDto = serviceEmprunter.searchByIdWithExemplaireAndLecteur(idEmprunt);
		if (emprunterDto != null) {
			return new ResponseEntity<EmprunterDto>(emprunterDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"emprunt not found\"}", HttpStatus.NOT_FOUND);
		}
	}
	
	/********** FIND BY ID */

	// exemple URL de déclenchement: ./api-emprunts/emprunt/MesEmprunts/1
	@GetMapping("MesEmprunts/{idPers}")
	public ResponseEntity<?> getListEmpruntofReader(@PathVariable("idPers") Long idPers) {
		List<EmprunterDtoEx2> listempruntsDto = serviceEmprunter.searchListofEmpruntByIdLecteur(idPers);
		if (listempruntsDto.size() != 0) {
			return new ResponseEntity<List<EmprunterDtoEx2>>(listempruntsDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{ \"err\" : \"ce lecteur n'a fait aucun emprunt\"}", HttpStatus.NOT_FOUND);
		}
	}

	/*********** FINDALL */
	@GetMapping("")
	public List<EmprunterDtoEx> getEmprunts() {
		return serviceEmprunter.searchAllDtoEx();

	}

	/*********** SAVE OR UPDATE*/

	@PostMapping("")
	public ResponseEntity<?> /*EmprunterDto*/ postEmprunt(@RequestBody EmprunterDtoEx nouvelEmprunt) {
		if(serviceEmprunter.saveOrUpdateEmpruntDtoEx(nouvelEmprunt) == null) {
			return new ResponseEntity<String>("{ \"err\" : \"Exemplaire non disponible\"}", HttpStatus.NOT_FOUND); 
		}
		else {
			return new ResponseEntity<EmprunterDto> (serviceEmprunter.saveOrUpdateEmpruntDtoEx(nouvelEmprunt),HttpStatus.OK);
		}
	}

	/********** UPDATE **/

	@PutMapping({ "", "/{idEmprunt}" })
	public ResponseEntity<?> putEmpruntToUpdate(@RequestBody EmprunterDtoEx emprunterDto,
			@PathVariable(value = "idEmprunt", required = false) Long idEmprunt) {
		Long idEmpruntToUpdate = idEmprunt != null ? idEmprunt : emprunterDto.getId();
		serviceEmprunter.shouldExistById(idEmpruntToUpdate); // remonte NotFoundException si pas trouvé
		if (emprunterDto.getId() == null)
			emprunterDto.setId(idEmpruntToUpdate);
		serviceEmprunter.saveOrUpdateEmpruntDtoEx(emprunterDto);
		return new ResponseEntity<EmprunterDto>(emprunterDto, HttpStatus.OK);
	}

	/********** DELETE **/

	@DeleteMapping("/{idEmprunt}")
	public ResponseEntity<?> deleteEmpruntById(@PathVariable("idEmprunt") Long idEmprunt) {
		Emprunter empruntAsupprimer = serviceEmprunter.searchById(idEmprunt);
		if (empruntAsupprimer == null)
			return new ResponseEntity<String>("{ \"err\" : \"emprunt(s) not found\"}", HttpStatus.NOT_FOUND); // NOT_FOUND
																												// code
																												// http
		else
			serviceEmprunter.deleteById(idEmprunt);
		return new ResponseEntity<String>("{ \"done\" : \"emprunt deleted\"}", HttpStatus.OK);
		// ou bien
		// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}