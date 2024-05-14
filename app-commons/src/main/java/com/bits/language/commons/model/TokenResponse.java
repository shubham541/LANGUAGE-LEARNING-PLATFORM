package com.bits.language.commons.model;

public class TokenResponse {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenResponse() {
	}

	public TokenResponse(String token) {
		this.token = token;
	}

	public TokenResponse token(String token) {
		setToken(token);
		return this;
	}

	@Override
	public String toString() {
		return "{" +
				" token='" + getToken() + "'" +
				"}";
	}

}
