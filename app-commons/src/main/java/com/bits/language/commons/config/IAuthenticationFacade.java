package com.bits.language.commons.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationFacade {

	String getCurrentUser();

	void setCurrentUser(UserDetails userDetails, HttpServletRequest request);
	
	String getAuthHeader();

}
