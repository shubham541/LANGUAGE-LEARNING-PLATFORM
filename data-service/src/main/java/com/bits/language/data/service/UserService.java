package com.bits.language.data.service;

import javax.validation.Valid;

import com.bits.language.commons.model.AuthUserDto;
import com.bits.language.commons.model.RegisterRequest;

public interface UserService {
	
	AuthUserDto findByUsernameOrEmail(String username, String email);

	String registerUser(@Valid RegisterRequest request);

	AuthUserDto findByUsername(String username);

	AuthUserDto updateAuthUser(@Valid AuthUserDto authUserDto);

	AuthUserDto findByEmail(String email);

}
