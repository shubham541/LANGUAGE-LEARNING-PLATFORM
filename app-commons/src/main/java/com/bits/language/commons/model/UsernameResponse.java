package com.bits.language.commons.model;

public class UsernameResponse {

	private String username;

	private String error;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UsernameResponse() {
		// for spring
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "UsernameResponse [username=" + username + ", error=" + error + "]";
	}

}
