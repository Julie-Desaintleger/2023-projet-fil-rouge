package com.inetum.AppBibliotheque.livres.services;

import java.util.List;

import com.inetum.AppBibliotheque.livres.dto.LivreDto;
import com.inetum.AppBibliotheque.livres.dto.LivreDtoEx;
import com.inetum.AppBibliotheque.livres.dto.LivreDtoEx2;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServiceLivre extends IGenericService<Livre, Long, LivreDto> {
	
	public LivreDtoEx2 searchByIdWithDomaine(Long idLivre);
	public List<LivreDtoEx2> searchAlldWithAllDomaine();
	public LivreDtoEx saveOrUpdateLivreDtoEx(LivreDtoEx livreDtoEx);
	public Livre searchByTitle(String titre);
 
}
