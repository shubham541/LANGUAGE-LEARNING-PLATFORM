package com.bits.language.commons.feign;

import javax.validation.Valid;

import com.bits.language.commons.constant.AppConstants;
import com.bits.language.commons.model.AuthUserDto;
import com.bits.language.commons.model.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "AuthUserFeign", url = "${db.users}")
public interface AuthUserFeign {

    @GetMapping(value = "/{username}/{email}", consumes = "application/json")
    AuthUserDto findByUsernameOrEmail(@PathVariable String username, @PathVariable String email);

    @PostMapping("/register")
    String register(@RequestBody @Valid RegisterRequest request);

    @GetMapping("/{username}")
    AuthUserDto findByUsername(@PathVariable String username);

    @PostMapping
    AuthUserDto updateAuthUser(@RequestBody @Valid AuthUserDto user);

    @GetMapping(value = "/email/{email}", produces = AppConstants.APPLICATION_JSON)
    AuthUserDto findByEmail(@PathVariable String email);

}
