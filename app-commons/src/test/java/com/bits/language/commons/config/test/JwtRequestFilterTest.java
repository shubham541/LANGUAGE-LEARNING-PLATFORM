package com.bits.language.commons.config.test;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bits.language.commons.config.AuthenticationFacade;
import com.bits.language.commons.config.JwtRequestFilter;
import com.bits.language.commons.model.UsernameResponse;
import com.bits.language.commons.service.JWTValidationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import feign.FeignException;

@SpringBootTest
@SpringBootConfiguration
class JwtRequestFilterTest {

	@Mock
	private UserDetailsService userDetailService;

	@Mock
	private JWTValidationService validationService;

	@Mock
	private AuthenticationFacade authFacade;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private FilterChain filterChain;

	@InjectMocks
	private JwtRequestFilter filter;

	@Test
	void doFilterInternalTest() throws ServletException, IOException {
		final String bearerToken = "Bearer some-token";
		final String username = "UserOne";
		final UsernameResponse validationResp = new UsernameResponse();
		validationResp.setUsername(username);
		when(request.getHeader("Authorization")).thenReturn(bearerToken);
		when(authFacade.getCurrentUser()).thenReturn(null);
		when(userDetailService.loadUserByUsername(username))
				.thenReturn(new User(username, username, new ArrayList<>()));
		when(validationService.validateToken(Mockito.anyString())).thenReturn(validationResp);
		filter.doFilter(request, response, filterChain);
	}

	@Test
	void doFilterInternalTest_NoBearer() throws ServletException, IOException {
		final String bearerToken = "some-token";
		when(request.getHeader("Authorization")).thenReturn(bearerToken);
		when(validationService.validateToken(Mockito.anyString())).thenReturn(null);
		filter.doFilter(request, response, filterChain);
		verify(validationService, never()).validateToken(Mockito.anyString());
	}

	@Test
	void doFilterInternalTest_FeignException() throws ServletException, IOException {
		final String bearerToken = "Bearer some-token";
		when(request.getHeader("Authorization")).thenReturn(bearerToken);
		when(validationService.validateToken(Mockito.anyString())).thenThrow(FeignException.class);
		when(userDetailService.loadUserByUsername(Mockito.anyString())).thenReturn(Mockito.any());
		filter.doFilter(request, response, filterChain);
		verify(userDetailService, never()).loadUserByUsername(Mockito.any());
	}

	@Test
	void doFilterInternalTest_ValidationErrors() throws ServletException, IOException {
		final String bearerToken = "Bearer some-token";
		final UsernameResponse validationResp = new UsernameResponse();
		validationResp.setError("Some error");
		when(request.getHeader("Authorization")).thenReturn(bearerToken);
		when(validationService.validateToken(Mockito.anyString())).thenReturn(validationResp);
		when(userDetailService.loadUserByUsername(Mockito.anyString())).thenReturn(Mockito.any());
		filter.doFilter(request, response, filterChain);
		verify(userDetailService, never()).loadUserByUsername(Mockito.any());
	}

	@Test
	void doFilterInternalTest_NullUsername() throws ServletException, IOException {
		final String bearerToken = "Bearer some-token";
		final UsernameResponse validationResp = new UsernameResponse();
		validationResp.setUsername(null);
		when(request.getHeader("Authorization")).thenReturn(bearerToken);
		when(validationService.validateToken(Mockito.anyString())).thenReturn(validationResp);
		when(userDetailService.loadUserByUsername(Mockito.anyString())).thenReturn(Mockito.any());
		filter.doFilter(request, response, filterChain);
		verify(userDetailService, never()).loadUserByUsername(Mockito.any());
	}
	

	@Test
	void doFilterInternalTest_LoggedInUser() throws ServletException, IOException {
		final String bearerToken = "Bearer some-token";
		final UsernameResponse validationResp = new UsernameResponse();
		validationResp.setUsername("Some User");
		when(request.getHeader("Authorization")).thenReturn(bearerToken);
		when(validationService.validateToken(Mockito.anyString())).thenReturn(validationResp);
		when(authFacade.getCurrentUser()).thenReturn("Some Other User");
		when(userDetailService.loadUserByUsername(Mockito.anyString())).thenReturn(Mockito.any());
		filter.doFilter(request, response, filterChain);
		verify(userDetailService, never()).loadUserByUsername(Mockito.any());
	}

}
