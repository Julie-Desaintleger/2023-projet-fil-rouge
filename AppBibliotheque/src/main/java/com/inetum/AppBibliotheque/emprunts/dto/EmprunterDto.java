package com.inetum.AppBibliotheque.emprunts.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class EmprunterDto {
	private Long  id;
	private  Date dateDebut;
	private Date dateFin;
	private String type;
	
	
	public EmprunterDto(Long id, Date dateDebut, Date dateFin, String type) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
	}
	
	
	

}
