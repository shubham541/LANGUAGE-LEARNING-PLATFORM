package com.bits.language.commons.model;

import javax.validation.constraints.NotBlank;

public class TokenValidationRequest {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenValidationRequest() {
	}

	public TokenValidationRequest(@NotBlank String token) {
		super();
		this.token = token;
	}

}
