package com.inetum.AppBibliotheque.personnes.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	private IDaoLecteur daoLecteur; // dao principal

	@Override
	public CrudRepository<Lecteur, Long> getdao() {
		return this.daoLecteur;
	}

}
