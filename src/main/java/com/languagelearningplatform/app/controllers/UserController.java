package com.languagelearningplatform.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.languagelearningplatform.app.dto.LoginDTO;
import com.languagelearningplatform.app.dto.UserDTO;
import com.languagelearningplatform.app.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
		return null;
        // Call UserService to register user
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginDTO loginDTO) {
		return null;
        // Call UserService to authenticate user
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody UserDTO userDTO) {
		return null;
        // Call UserService to update user
    }
}
