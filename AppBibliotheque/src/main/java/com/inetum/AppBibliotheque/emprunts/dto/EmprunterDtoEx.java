package com.inetum.AppBibliotheque.emprunts.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class EmprunterDtoEx extends EmprunterDto {
	private Long idExemp;
	private Long idLecteur;

}
