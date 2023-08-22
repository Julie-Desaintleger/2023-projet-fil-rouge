package com.inetum.AppBibliotheque.livres.services;

import com.inetum.AppBibliotheque.livres.Dto.LivreDto;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServiceLivre extends IGenericService<Livre, Long, LivreDto> {

}
