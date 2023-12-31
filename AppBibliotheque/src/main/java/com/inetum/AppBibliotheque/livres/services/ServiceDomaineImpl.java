package com.inetum.AppBibliotheque.livres.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoDomaine;
import com.inetum.AppBibliotheque.livres.dto.DomaineDto;
import com.inetum.AppBibliotheque.livres.entities.Domaine;
import com.inetum.AppBibliotheque.services.AbstractGenericService;


@Service
@Transactional
public class ServiceDomaineImpl extends AbstractGenericService<Domaine, Long, DomaineDto> implements IServiceDomaine {

	@Override
	public CrudRepository<Domaine, Long> getdao() {
		return this.daoDomaine;
	}

	@Override
	public Class<DomaineDto> getDtoClass() {
		return DomaineDto.class;
	}

	Logger logger = LoggerFactory.getLogger(ServiceDomaineImpl.class);

	@Autowired
	private IDaoDomaine daoDomaine;

}
