package com.inetum.AppBibliotheque.services;

import java.util.List;

public interface IGenericService<E,ID,DTO> {
	public E searchById(ID id);
	public DTO searchDtoById(ID id) ;
	public E saveOrUpdate(E entity);
	public void deleteById(ID id);
	void shouldExistById(ID id) ;
	public boolean existById(ID id);
	List<E> searchAll();
	public List<DTO> searchAllDto();
	

}
