package com.inetum.AppBibliotheque.emprunts.services;

import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDto;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServiceEmprunter extends IGenericService<Emprunter, Long, EmprunterDto> {
	

}
