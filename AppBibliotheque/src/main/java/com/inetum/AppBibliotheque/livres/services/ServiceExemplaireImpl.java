package com.inetum.AppBibliotheque.livres.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.inetum.AppBibliotheque.livres.Dto.ExemplaireDto;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire.EtatLivre;
import com.inetum.AppBibliotheque.services.AbstractGenericService;

@Service
@Transactional
public class ServiceExemplaireImpl extends AbstractGenericService<Exemplaire, Long, ExemplaireDto> implements IServiceExemplaire {
	
	@Override
	public CrudRepository<Exemplaire, Long> getdao() {
		return this.daoExemplaire;
	}
	
	@Override
	public Class<ExemplaireDto> getDtoClass() {
		return ExemplaireDto.class;
	}
	
	Logger logger = LoggerFactory.getLogger(ServiceExemplaireImpl.class);
	
	@Autowired
	private IDaoExemplaire daoExemplaire;

	@Override
	public List<Exemplaire> CheckDisponibility(EtatLivre etat) {
		return daoExemplaire.getDisponibilite(etat);
	}

	

	


	
	
}
