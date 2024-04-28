package com.bits.language.commons.service;

import com.bits.language.commons.feign.AuthFeign;
import com.bits.language.commons.logger.LogExecutionTime;
import com.bits.language.commons.model.UsernameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.language.commons.model.TokenValidationRequest;

@Service
public class JWTValidationServiceImpl implements JWTValidationService {

	@Autowired
	private AuthFeign authFeign;

	@Override
	@LogExecutionTime
	public UsernameResponse validateToken(String token) {
		return authFeign.validateToken(new TokenValidationRequest(token));
	}

}
