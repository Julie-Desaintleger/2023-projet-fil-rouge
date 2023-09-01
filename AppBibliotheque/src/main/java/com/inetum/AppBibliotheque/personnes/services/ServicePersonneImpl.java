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
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoPersonne;
import com.inetum.AppBibliotheque.personnes.dto.PersonneDto;
import com.inetum.AppBibliotheque.personnes.entities.Personne;
import com.inetum.AppBibliotheque.services.AbstractGenericService;

@Service
@Transactional
public class ServicePersonneImpl extends AbstractGenericService<Personne, Long, PersonneDto>
			implements IServicePersonne {

	@Override
	public Class<PersonneDto> getDtoClass() {
		return PersonneDto.class;
	}

	Logger logger = LoggerFactory.getLogger(ServicePersonneImpl.class);

	private DtoConverter dtoConverter = new DtoConverter();

	@Autowired
	private IDaoPersonne daoPersonne; // dao principal

	@Override
	public CrudRepository<Personne, Long> getdao() {
		return this.daoPersonne;
	}

	@Override
	public Personne rechercherPersonneParEmail(String email) {
		return daoPersonne.findByEmail(email);
	}

	@Override
	public List<Personne> rechercherPersonneParNom(String nom) {
		return daoPersonne.findByNom(nom);
	}

	@Override
	public List<Personne> rechercherPersonneParNomEtPrenom(String nom, String prenom) {
		return daoPersonne.findByNomAndPrenom(nom, prenom);
	}

	@Override
	public PersonneDto saveOrUpdateDto(PersonneDto personneDto) {
		Personne personneToSaveOrUpdate = dtoConverter.personneDtoToPersonne(personneDto);
		return GenericConverter.map(this.saveOrUpdate(personneToSaveOrUpdate), getDtoClass());
	}
}
