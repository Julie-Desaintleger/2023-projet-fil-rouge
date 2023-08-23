package com.inetum.AppBibliotheque.converter;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.inetum.AppBibliotheque.livres.Dto.DomaineDto;
import com.inetum.AppBibliotheque.livres.Dto.ExemplaireDto;
import com.inetum.AppBibliotheque.livres.Dto.ExemplaireDtoEx2;
import com.inetum.AppBibliotheque.livres.Dto.LivreDto;
import com.inetum.AppBibliotheque.livres.Dto.LivreDtoEx2;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
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
	
	
	
	public /*static*/ List<ExemplaireDto> exemplaireToExemplaireDto(List<Exemplaire> entityList) {
		return entityList.stream()
				.map((entity)->exemplaireToExemplaireDto(entity))
				.toList();
	}
	
	public /*static*/ ExemplaireDto exemplaireToExemplaireDto(Exemplaire  entity) {
		return new ExemplaireDto(entity.getIdExemp() , 
				entity.getEtat().toString(),
				entity.getIsDisponibilite(),
				entity.getLabel());
	}
	
	public /*static*/ ExemplaireDtoEx2 exemplaireToExemplaireDtoEx2(Exemplaire entity) {
		ExemplaireDtoEx2 exemplaireDto = new ExemplaireDtoEx2();
		BeanUtils.copyProperties(entity, exemplaireDto);
		exemplaireDto.setLivre(GenericConverter.map(entity.getLivre(), LivreDto.class));
		return exemplaireDto;
	}
}


