package com.languagelearningplatform.app.services;

import com.languagelearningplatform.app.dto.LoginDTO;
import com.languagelearningplatform.app.dto.UserDTO;

public interface UserService {
    void registerUser(UserDTO userDTO);
    boolean authenticateUser(LoginDTO loginDTO);
    void updateUser(UserDTO userDTO);
}
