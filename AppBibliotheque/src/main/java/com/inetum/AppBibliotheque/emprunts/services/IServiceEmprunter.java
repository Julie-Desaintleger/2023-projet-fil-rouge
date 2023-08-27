package com.inetum.AppBibliotheque.emprunts.services;

import java.util.List;

import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDto;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDtoEx;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDtoEx2;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;
import com.inetum.AppBibliotheque.services.IGenericService;

public interface IServiceEmprunter extends IGenericService<Emprunter, Long, EmprunterDto> {
	public EmprunterDtoEx2 searchByIdWithExemplaireAndLecteur(Long idEmprunter);
	public EmprunterDtoEx saveOrUpdateEmpruntDtoEx(EmprunterDtoEx emprunterDtoEx);
	public List<EmprunterDtoEx> searchAllDtoEx();

}
