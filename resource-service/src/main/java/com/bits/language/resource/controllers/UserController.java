package com.bits.language.resource.controllers;

import com.bits.language.resource.dto.LoginDTO;
import com.bits.language.resource.dto.UserDTO;
import com.bits.language.resource.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
