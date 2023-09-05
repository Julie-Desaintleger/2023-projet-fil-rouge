package com.inetum.AppBibliotheque.personnes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
	private String email;
	private String password;

	public LoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

}
