package com.inetum.AppBibliotheque.emprunts.services;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.inetum.AppBibliotheque.emprunts.dao.interfaces.IDaoEmprunt;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDto;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;
import com.inetum.AppBibliotheque.services.AbstractGenericService;

@Service
@Transactional
public class ServiceEmprunterImpl extends AbstractGenericService<Emprunter, Long, EmprunterDto>
		implements IServiceEmprunter {

	@Override
	public CrudRepository<Emprunter, Long> getdao() {
		return this.daoEmprunt;
	}

	@Override
	public Class<EmprunterDto> getDtoClass() {
		return EmprunterDto.class;
	}

	private IDaoEmprunt daoEmprunt;

}
