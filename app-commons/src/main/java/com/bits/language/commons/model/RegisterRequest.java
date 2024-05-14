package com.bits.language.commons.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterRequest extends UserInfoDto {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 8, max = 12)
	private String password;

	@NotBlank
	private String securityAnswer;

	@NotBlank
	private String securityQn;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurityQn() {
		return securityQn;
	}

	public void setSecurityQn(String securityQn) {
		this.securityQn = securityQn;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RegisterRequest username(String username) {
		setUsername(username);
		return this;
	}

	public RegisterRequest password(String password) {
		setPassword(password);
		return this;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
}
