package com.languagelearningplatform.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.languagelearningplatform.app.dto.LoginDTO;
import com.languagelearningplatform.app.dto.UserDTO;
import com.languagelearningplatform.app.model.User;
import com.languagelearningplatform.app.repository.UserRepository;
import com.languagelearningplatform.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(UserDTO userDTO) {
        // Convert DTO to entity and save in database
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
    //    userRepository.save(user);
    }

    @Override
    public boolean authenticateUser(LoginDTO loginDTO) {
		return false;
        // Check if user with given username and password exists in the database
       // User user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
      //  return user != null;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        // Fetch user from database, update fields, and save
//        User user = userRepository.findByUsername(userDTO.getUsername());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        userRepository.save(user);
    }
}
