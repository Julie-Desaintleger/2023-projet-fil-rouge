package com.inetum.AppBibliotheque.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.inetum.AppBibliotheque.converter.MyConverter;

public abstract class AbstractGenericService<E, ID, DTO> implements IGenericService<E, ID, DTO> {

	public abstract CrudRepository<E, ID> getdao(); // dao principal

	public abstract Class<DTO> getDtoClass(); // dto principal

	@Override
	public E searchById(ID id) {
		return getdao().findById(id).orElse(null);
	}

	@Override

	public DTO searchDtoById(ID id) {
		E e = this.searchById(id);
		if (e != null) {
			return MyConverter.map(e, getDtoClass());

		}
	else
		
		return null;
			//throw new NotFoundException("entity not found for id=" + id);
		
	}

	@Override
	public E saveOrUpdate(E entity) {
		return getdao().save(entity);
	}

	@Override
	public void deleteById(ID id) {
		/*
		 * if(!(getDao().existsById(id))) throw new
		 * NotFoundException("no entity to delete for id=" + id);
		 */
		// shouldExistById(id);
		/* else */
		getdao().deleteById(id);
	}

	@Override
	public boolean existById(ID id) {
		return getdao().existsById(id);
	}

	public void shouldExistById(ID id) {
		// if(!(getdao().existsById(id)))
		// throw new NotFoundException("no entity exists for id=" + id);
	}

	@Override
	public List<E> searchAll() {
		return (List<E>) getdao().findAll();
	}

	public List<DTO> searchAllDto() {
		return MyConverter.map(this.searchAll(), getDtoClass()); // ex: dtoClass = CompteDto.class
	}

}
