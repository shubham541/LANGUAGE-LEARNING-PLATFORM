package com.bits.language.resource.controllers;

import com.bits.language.commons.annotation.Username;
import com.bits.language.commons.constant.AppConstants;
import com.bits.language.commons.logger.LogExecutionTime;
import com.bits.language.commons.model.AuthUserDto;
import com.bits.language.commons.model.RegisterRequest;
import com.bits.language.resource.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("${db.users}")
@Validated
public class UsersController {

	@Autowired
	private UserService userService;

	@LogExecutionTime
	@GetMapping(value = "/{username}/{email}", produces = AppConstants.APPLICATION_JSON)
	public AuthUserDto findByUsernameOrEmail(@PathVariable @Username String username,
			@PathVariable @Email String email) {
		return userService.findByUsernameOrEmail(username, email);
	}

	@LogExecutionTime
	@PostMapping(value = "/register", produces = AppConstants.APPLICATION_JSON)
	public String registerUser(@RequestBody @Valid RegisterRequest request) {
		return userService.registerUser(request);
	}

	@LogExecutionTime
	@GetMapping(value = "/{username}", produces = AppConstants.APPLICATION_JSON)
	public AuthUserDto findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}

	@LogExecutionTime
	@GetMapping(value = "/email/{email}", produces = AppConstants.APPLICATION_JSON)
	public AuthUserDto findByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}

	@LogExecutionTime
	@PostMapping(produces = AppConstants.APPLICATION_JSON)
	public AuthUserDto updateAuthUser(@RequestBody @Valid AuthUserDto authUserDto) {
		return userService.updateAuthUser(authUserDto);
	}

}
