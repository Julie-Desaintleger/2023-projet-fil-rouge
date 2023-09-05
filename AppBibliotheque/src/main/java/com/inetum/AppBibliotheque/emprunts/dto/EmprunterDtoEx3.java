package com.inetum.AppBibliotheque.emprunts.dto;

import com.inetum.AppBibliotheque.livres.dto.ExemplaireDtoEx2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class EmprunterDtoEx3 extends EmprunterDtoEx2 {
	private ExemplaireDtoEx2 exemplaire;


}
