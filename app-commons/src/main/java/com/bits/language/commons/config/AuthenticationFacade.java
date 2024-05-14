package com.bits.language.commons.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public String getCurrentUser() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth == null ? null : auth.getName();
	}

	@Override
	public void setCurrentUser(UserDetails userDetails, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}

	@Override
	public String getAuthHeader() {
		if (getCurrentUser() != null) {
			final var reqAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			if (reqAttr != null) {
				return reqAttr.getRequest().getHeader("Authorization");
			}
		}
		return null;
	}

}
