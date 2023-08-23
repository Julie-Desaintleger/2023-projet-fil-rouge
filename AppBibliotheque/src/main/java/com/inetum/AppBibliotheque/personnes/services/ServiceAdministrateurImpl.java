package com.inetum.AppBibliotheque.personnes.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoAdministrateur;
import com.inetum.AppBibliotheque.personnes.dto.AdministrateurDto;
import com.inetum.AppBibliotheque.personnes.entities.Administrateur;
import com.inetum.AppBibliotheque.services.AbstractGenericService;

@Service
@Transactional
public class ServiceAdministrateurImpl extends AbstractGenericService<Administrateur, Long, AdministrateurDto>
		implements IServiceAdministrateur {

	@Override
	public Class<AdministrateurDto> getDtoClass() {
		return AdministrateurDto.class;
	}

	Logger logger = LoggerFactory.getLogger(ServiceAdministrateurImpl.class);

	@Autowired
	private IDaoAdministrateur daoAdministrateur; // dao principal

	@Override
	public CrudRepository<Administrateur, Long> getdao() {
		return this.daoAdministrateur;
	}

}
