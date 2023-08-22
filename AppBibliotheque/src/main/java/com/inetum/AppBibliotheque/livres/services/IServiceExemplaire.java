package com.inetum.AppBibliotheque.livres.services;

import java.util.List;

import com.inetum.AppBibliotheque.livres.Dto.ExemplaireDto;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire.EtatLivre;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServiceExemplaire extends IGenericService<Exemplaire, Long, ExemplaireDto> {
	
	List<Exemplaire> CheckDisponibility(EtatLivre etat);

}
