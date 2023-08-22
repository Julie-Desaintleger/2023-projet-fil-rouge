package com.inetum.AppBibliotheque.converter;

import java.util.List;

import com.inetum.AppBibliotheque.livres.Dto.LivreDto;
import com.inetum.AppBibliotheque.livres.entities.Livre;

public class MyConverter extends GenericConverter {

	// GenericConverter.map(compteEntity,CompteDto.class)
	public static <S, D> D map(S source, Class<D> targetClass) {
		D dest = GenericConverter.map(source, targetClass);
	
		if(source instanceof Livre livre) {
			LivreDto livreDto = (LivreDto) dest;
		    livreDto.setIdDomaine(livre.getDomaine().getId());
		}
		
		return dest;
	}

	// GenericConverter.map(ListeCompteEntity,CompteDto.class)
	public static <S, D> List<D> map(List<S> sourceList, Class<D> targetClass) {
		return sourceList.stream().map((source) -> map(source, targetClass)).toList();
	}

}