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
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoDomaine;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.dto.LivreDto;
import com.inetum.AppBibliotheque.livres.dto.LivreDtoEx;
import com.inetum.AppBibliotheque.livres.dto.LivreDtoEx2;
import com.inetum.AppBibliotheque.livres.entities.Domaine;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.services.AbstractGenericService;

@Service
@Transactional
public class ServiceLivreImpl extends AbstractGenericService<Livre, Long, LivreDto> implements IServiceLivre {
	private DtoConverter dtoConverter = new DtoConverter();

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
	@Autowired
	private IDaoDomaine daoDomaine;

	@Override
	public LivreDtoEx2 searchByIdWithDomaine(Long idLivre) {
		Livre entityLivre = searchById(idLivre);
		return dtoConverter.LivreToLivreDtoEx2(entityLivre);

	}

	@Override
	public LivreDtoEx saveOrUpdateLivreDtoEx(LivreDtoEx livreDtoEx) {
		Livre livreEntity = GenericConverter.map(livreDtoEx, Livre.class);
				if(livreDtoEx.getIdDomaine() !=null) {
					Domaine domaineEntity = daoDomaine.findById(livreDtoEx.getIdDomaine()).get();
					livreEntity.setDomaine(domaineEntity);
					
				}
				daoLivre.save(livreEntity); //NB: à ce moment là , 
		                                      //éventuelle auto_incr de LivreEntity.numero
				livreDtoEx.setIdLivre(livreEntity.getIdLivre());
				return livreDtoEx; //on retourne le DtoEx sauvegardé
				                    //avec la clef primaire éventuellement autoincrémenté
			}

	@Override
	public List<LivreDtoEx2> searchAlldWithAllDomaine() {
		return dtoConverter.LivreToLivreDtoEx2(searchAll());
	
	}

	@Override
	public LivreDtoEx2 searchByTitle(String titre) {
		return dtoConverter.LivreToLivreDtoEx2(daoLivre.findByTitre(titre)) ;
		
	}
	}

	


