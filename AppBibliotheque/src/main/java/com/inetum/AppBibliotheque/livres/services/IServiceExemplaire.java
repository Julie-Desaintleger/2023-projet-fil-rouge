package com.inetum.AppBibliotheque.livres.services;

import java.util.List;

import com.inetum.AppBibliotheque.livres.dto.ExemplaireDto;
import com.inetum.AppBibliotheque.livres.dto.ExemplaireDtoEx;
import com.inetum.AppBibliotheque.livres.dto.ExemplaireDtoEx2;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire.EtatLivre;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServiceExemplaire extends IGenericService<Exemplaire, Long, ExemplaireDto> {
	
	List<Exemplaire> CheckDisponibility(EtatLivre etat);
	public ExemplaireDtoEx2 searchByIdWithLivre(Long idExemp);
	public List<ExemplaireDtoEx2> searchAllWithAllLivre();
	public ExemplaireDtoEx saveOrUpdateExemplaireDtoEx(ExemplaireDtoEx exemplaireDtoEx);
	public List<ExemplaireDtoEx2> findByBookTitle(String titre);

}
