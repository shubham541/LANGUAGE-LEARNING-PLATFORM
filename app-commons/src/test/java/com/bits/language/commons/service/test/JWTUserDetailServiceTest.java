package com.bits.language.commons.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bits.language.commons.feign.AuthUserFeign;
import com.bits.language.commons.model.AuthUserDto;
import com.bits.language.commons.service.JWTUserDetailService;

@SpringBootTest
class JWTUserDetailServiceTest {

	@Mock
	private AuthUserFeign authUserDelegate;

	@InjectMocks
	private JWTUserDetailService detailService;

	@Test
	void loadUserByUsernameTest() {
		final String username = "username";
		final String password = "password";
		AuthUserDto response = new AuthUserDto();
		response.setPassword(password);
		response.setUsername(username);
		when(authUserDelegate.findByUsername(username)).thenReturn(response);
		final var result = detailService.loadUserByUsername(username);
		assertNotNull(result);
		assertEquals(username, result.getUsername());
		assertEquals(password, result.getPassword());
		assertEquals(0, result.getAuthorities().size());
	}

	@Test
	void loadUserByUsername_ExceptionTest() {
		final String username = "username";
		when(authUserDelegate.findByUsername(username)).thenReturn(null);
		assertThrows(UsernameNotFoundException.class, () -> detailService.loadUserByUsername(username));
	}

}
