package com.bits.language.commons.feign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bits.language.commons.model.TokenValidationRequest;
import com.bits.language.commons.model.UsernameResponse;

@FeignClient(value = "AuthFeign", url = "${auth.path}")
public interface AuthFeign {
	
	@PostMapping(value = "${auth.path.validate}")
	public UsernameResponse validateToken(@Valid @RequestBody TokenValidationRequest request);

}
