package com.inetum.AppBibliotheque.emprunts.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.inetum.AppBibliotheque.converter.DtoConverter;
import com.inetum.AppBibliotheque.converter.GenericConverter;
import com.inetum.AppBibliotheque.emprunts.dao.interfaces.IDaoEmprunt;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDto;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDtoEx;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDtoEx2;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoLecteur;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;
import com.inetum.AppBibliotheque.services.AbstractGenericService;

@Service
@Transactional
public class ServiceEmprunterImpl extends AbstractGenericService<Emprunter, Long, EmprunterDto>
		implements IServiceEmprunter {
	
	private DtoConverter dtoConverter = new DtoConverter();

	@Override
	public CrudRepository<Emprunter, Long> getdao() {
		return this.daoEmprunt;
	}

	@Override
	public Class<EmprunterDto> getDtoClass() {
		return EmprunterDto.class;
	}

	@Autowired
	private IDaoEmprunt daoEmprunt;
	@Autowired
	private IDaoExemplaire daoExemplaire;
	@Autowired
	private IDaoLecteur daoLecteur;

	@Override
	public EmprunterDtoEx2 searchByIdWithExemplaireAndLecteur(Long idEmprunter) {
		Emprunter entityEmprunt = searchById(idEmprunter);
		return dtoConverter.emprunterToEmprunterDtoEx2(entityEmprunt);
	}

	@Override
	public EmprunterDtoEx saveOrUpdateEmpruntDtoEx(EmprunterDtoEx emprunterDtoEx) {
		Emprunter empruntEntity = GenericConverter.map(emprunterDtoEx, Emprunter.class);
		if (emprunterDtoEx.getIdExemp() != null && emprunterDtoEx.getIdLecteur() != null) {
			Exemplaire exemplaireEntity = daoExemplaire.findById(emprunterDtoEx.getIdExemp()).get();
			empruntEntity.setExemplaireEmprunte(exemplaireEntity);
			Lecteur LecteurEntity = daoLecteur.findById(emprunterDtoEx.getIdLecteur()).get();
			empruntEntity.setEmprunteur(LecteurEntity);
		}

		empruntEntity.setType(Emprunter.TypeEmprunt.valueOf(emprunterDtoEx.getType()));

		// AVANT DE CREER UN EMPRUNT, IL FAUT SE RASSURER QUE L'EXEMPLAIRE EST
		// DISPONIBLE
		Exemplaire exemplaireEntity = daoExemplaire.findById(emprunterDtoEx.getIdExemp()).get();
		if (exemplaireEntity.getIsDisponibilite().equals(true)) {
			// LORSQU'UN EXEMPLAIRE EST EMPRUNTE, ON CHANGE LA VALEUR DE LA
			// DISPONIBILITE(isDisponobilite =false) DE L'EXEMPLAIRE
			// PUIS ON ENREGISTRE EN BASE
			exemplaireEntity.setIsDisponibilite(false);
			daoExemplaire.save(exemplaireEntity);
			daoEmprunt.save(empruntEntity);

			emprunterDtoEx.setId(empruntEntity.getId());
			return emprunterDtoEx; // on retourne le DtoEx sauvegardé
			// avec la clef primaire éventuellement autoincrémenté
		}else
			   
			return null;//A améliorer
	}

	@Override
	public List<EmprunterDtoEx> searchAllDtoEx() {
		return dtoConverter.emprunterToEmprunterDtoEx(searchAll());
	}

}
