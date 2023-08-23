package com.inetum.AppBibliotheque.personnes.services;

import com.inetum.AppBibliotheque.personnes.dto.PersonneDto;
import com.inetum.AppBibliotheque.personnes.entities.Personne;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServicePersonne extends IGenericService<Personne, Long, PersonneDto> {

}
