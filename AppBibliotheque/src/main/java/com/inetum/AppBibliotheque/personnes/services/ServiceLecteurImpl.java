package com.inetum.AppBibliotheque.personnes.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inetum.AppBibliotheque.converter.DtoConverter;
import com.inetum.AppBibliotheque.converter.GenericConverter;
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoLecteur;
import com.inetum.AppBibliotheque.personnes.dto.LecteurDto;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;
import com.inetum.AppBibliotheque.services.AbstractGenericService;

@Service
@Transactional
public class ServiceLecteurImpl extends AbstractGenericService<Lecteur, Long, LecteurDto>
			implements IServiceLecteur {

	@Override
	public Class<LecteurDto> getDtoClass() {
		return LecteurDto.class;
	}

	Logger logger = LoggerFactory.getLogger(ServiceLecteurImpl.class);

	private DtoConverter dtoConverter = new DtoConverter();

	@Autowired
	private IDaoLecteur daoLecteur; // dao principal

	@Override
	public CrudRepository<Lecteur, Long> getdao() {
		return this.daoLecteur;
	}

	@Override
	public Lecteur rechercherPersonneParEmail(String email) {
		return daoLecteur.findByEmail(email);
	}

	@Override
	public List<Lecteur> rechercherPersonneParNom(String nom) {
		return daoLecteur.findByNom(nom);
	}

	@Override
	public List<Lecteur> rechercherPersonneParNomEtPrenom(String nom, String prenom) {
		return daoLecteur.findByNomAndPrenom(nom, prenom);
	}

	@Override
	public LecteurDto saveOrUpdateDto(LecteurDto lecteurDto) {
		Lecteur lecteurToSaveOrUpdate = dtoConverter.lecteurDtoToLecteur(lecteurDto);
		return GenericConverter.map(this.saveOrUpdate(lecteurToSaveOrUpdate), getDtoClass());
	}

}
