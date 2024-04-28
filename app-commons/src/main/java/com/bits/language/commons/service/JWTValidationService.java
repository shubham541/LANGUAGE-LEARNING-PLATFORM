package com.bits.language.commons.service;

import com.bits.language.commons.model.UsernameResponse;

public interface JWTValidationService {

	public UsernameResponse validateToken(String token);

}
