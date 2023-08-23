package com.inetum.AppBibliotheque.converter;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.inetum.AppBibliotheque.livres.Dto.DomaineDto;
import com.inetum.AppBibliotheque.livres.Dto.LivreDto;
import com.inetum.AppBibliotheque.livres.Dto.LivreDtoEx2;
import com.inetum.AppBibliotheque.livres.entities.Livre;

public class DtoConverter {

	public /*static*/ List<LivreDto> LivreToLivreDto(List<Livre> entityList) {
		return entityList.stream()
				         .map((entity)->LivreToLivreDto(entity))
				         .toList();
	}

	public /*static*/ LivreDto LivreToLivreDto(Livre entity) {
		return new LivreDto(entity.getIdLivre() , 
				             entity.getTitre(),
				             entity.getAuteur(),
				             entity.getEditeur());
	}

	public /*static*/ LivreDtoEx2 LivreToLivreDtoEx2(Livre entity) {
		LivreDtoEx2 livreDto = new LivreDtoEx2();
	BeanUtils.copyProperties(entity, livreDto);
	livreDto.setDomaine(GenericConverter.map(entity.getDomaine(), DomaineDto.class));
	return livreDto;
	}
}


