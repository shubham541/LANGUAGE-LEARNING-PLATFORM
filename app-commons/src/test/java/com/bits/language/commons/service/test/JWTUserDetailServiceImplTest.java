package com.bits.language.commons.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.bits.language.commons.feign.AuthFeign;
import com.bits.language.commons.model.UsernameResponse;
import com.bits.language.commons.service.JWTValidationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootConfiguration
@SpringBootTest
class JWTUserDetailServiceImplTest {

	@Mock
	private AuthFeign authFeign;

	@InjectMocks
	private JWTValidationServiceImpl serviceImpl;

	@Test
	void validateTokenTest() {
		UsernameResponse response = new UsernameResponse();
		when(authFeign.validateToken(Mockito.any())).thenReturn(response);
		assertNotNull(serviceImpl.validateToken("Some Token"));
	}

}
