package com.inetum.AppBibliotheque.personnes.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	Logger logger = LoggerFactory.getLogger(ServiceLecteurImpl.class);

	@Autowired
	private IDaoPersonne daoPersonne; // dao principal

	@Override
	public CrudRepository<Personne, Long> getdao() {
		return this.daoPersonne;
	}

}
