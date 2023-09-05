package com.inetum.AppBibliotheque.personnes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
	private String email;
	private Boolean isConnected = false;
	private String msg = "Aucun utilisateur connecté";
	private String role;

	public LoginResponse(String email) {
		super();
		this.email = email;
	}

}
