package com.inetum.AppBibliotheque.emprunts.dto;

import com.inetum.AppBibliotheque.livres.dto.ExemplaireDto;
import com.inetum.AppBibliotheque.personnes.dto.LecteurDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class EmprunterDtoEx2 extends EmprunterDto {
	private ExemplaireDto exemplaire;
	private LecteurDto lecteur;

}
