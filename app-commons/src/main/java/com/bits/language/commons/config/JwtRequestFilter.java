package com.bits.language.commons.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bits.language.commons.service.JWTValidationService;

import feign.FeignException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private JWTValidationService validationService;

	@Autowired
	private AuthenticationFacade authFacade;

	private final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String header = request.getHeader("Authorization");
		String error = null;
		if (StringUtils.startsWith(header, "Bearer ")) {
			final String token = header.substring(7);
			try {
				final var result = validationService.validateToken(token);
				if (StringUtils.isBlank(result.getError())) {
					final String username = result.getUsername();
					if (username != null && authFacade.getCurrentUser() == null) {
						final UserDetails userDetails = userDetailService.loadUserByUsername(username);
						authFacade.setCurrentUser(userDetails, request);
					}
				} else {
					error = result.getError();
				}
			} catch (FeignException ex) {
				error = ex.getMessage();
			}
		}
		if (error != null) {
			log.error("doFilterInternal: error -> {}", error);
		}
		filterChain.doFilter(request, response);

	}

}
