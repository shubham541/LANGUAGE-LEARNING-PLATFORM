package com.bits.language.resource.services;

import com.bits.language.resource.dto.LoginDTO;
import com.bits.language.resource.dto.UserDTO;

public interface UserService {
    void registerUser(UserDTO userDTO);
    boolean authenticateUser(LoginDTO loginDTO);
    void updateUser(UserDTO userDTO);
}
