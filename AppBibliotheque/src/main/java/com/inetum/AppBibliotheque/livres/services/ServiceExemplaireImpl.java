package com.inetum.AppBibliotheque.livres.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.inetum.AppBibliotheque.converter.DtoConverter;
import com.inetum.AppBibliotheque.converter.GenericConverter;
import com.inetum.AppBibliotheque.livres.Dto.ExemplaireDto;
import com.inetum.AppBibliotheque.livres.Dto.ExemplaireDtoEx;
import com.inetum.AppBibliotheque.livres.Dto.ExemplaireDtoEx2;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire.EtatLivre;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.services.AbstractGenericService;

@Service
@Transactional
public class ServiceExemplaireImpl extends AbstractGenericService<Exemplaire, Long, ExemplaireDto>
		implements IServiceExemplaire {

	private DtoConverter dtoConverter = new DtoConverter();

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

	@Autowired
	private IDaoLivre daoLivre;

	@Override
	public List<Exemplaire> CheckDisponibility(EtatLivre etat) {
		return daoExemplaire.getDisponibilite(etat);
	}

	@Override
	public ExemplaireDtoEx2 searchByIdWithLivre(Long idExemp) {
		Exemplaire entityExempalire = searchById(idExemp);
		return dtoConverter.exemplaireToExemplaireDtoEx2(entityExempalire);
	}

	@Override
	public ExemplaireDtoEx saveOrUpdateExemplaireDtoEx(ExemplaireDtoEx exemplaireDtoEx) {
		Exemplaire exemplaireEntity = GenericConverter.map(exemplaireDtoEx, Exemplaire.class);
		if (exemplaireDtoEx.getIdLiv() != null) {
			Livre livreEntity = daoLivre.findById(exemplaireDtoEx.getIdLiv()).get();
			exemplaireEntity.setLivre(livreEntity);

		}
		exemplaireEntity.setEtat(Exemplaire.EtatLivre.valueOf(exemplaireDtoEx.getEtat()));
		daoExemplaire.save(exemplaireEntity); // NB: à ce moment là ,
		// éventuelle auto_incr de LivreEntity.numero
		exemplaireDtoEx.setIdExemp(exemplaireEntity.getIdExemp());
		return exemplaireDtoEx; // on retourne le DtoEx sauvegardé
		// avec la clef primaire éventuellement autoincrémenté
	}

}
