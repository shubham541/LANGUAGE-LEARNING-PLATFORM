package com.bits.language.resource.services.impl;

import com.bits.language.resource.dto.LoginDTO;
import com.bits.language.resource.dto.UserDTO;
import com.bits.language.resource.model.User;
import com.bits.language.resource.repository.UserRepository;
import com.bits.language.resource.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
