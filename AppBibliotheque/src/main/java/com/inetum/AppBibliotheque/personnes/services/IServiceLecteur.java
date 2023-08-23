package com.inetum.AppBibliotheque.personnes.services;

import com.inetum.AppBibliotheque.personnes.dto.LecteurDto;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServiceLecteur extends IGenericService<Lecteur, Long, LecteurDto> {

}
