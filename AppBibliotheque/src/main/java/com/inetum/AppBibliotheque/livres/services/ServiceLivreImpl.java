package com.inetum.AppBibliotheque.livres.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.inetum.AppBibliotheque.livres.Dto.LivreDto;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.services.AbstractGenericService;
@Service
@Transactional
public class ServiceLivreImpl extends AbstractGenericService<Livre, Long, LivreDto> implements IServiceLivre {
	
	@Override
	public CrudRepository<Livre, Long> getdao() {
		return this.daoLivre;
	}
	
	@Override
	public Class<LivreDto> getDtoClass() {
		return LivreDto.class;
	}
	
	Logger logger = LoggerFactory.getLogger(ServiceLivreImpl.class);
	
	@Autowired
	private IDaoLivre daoLivre;

	

	


	
	
}
