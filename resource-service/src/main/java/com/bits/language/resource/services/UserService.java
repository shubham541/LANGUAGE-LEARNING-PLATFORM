package com.bits.language.resource.services;

import com.bits.language.commons.model.AuthUserDto;
import com.bits.language.commons.model.RegisterRequest;
import com.bits.language.resource.dto.LoginDTO;
import com.bits.language.resource.dto.UserDTO;

import javax.validation.Valid;

public interface UserService {
    AuthUserDto findByUsernameOrEmail(String username, String email);

    String registerUser(@Valid RegisterRequest request);

    AuthUserDto findByUsername(String username);

    AuthUserDto updateAuthUser(@Valid AuthUserDto authUserDto);

    AuthUserDto findByEmail(String email);

}
