package com.bits.language.resource.services.helper;

import com.bits.language.commons.model.AuthUserDto;
import com.bits.language.commons.model.RegisterRequest;
import com.bits.language.resource.model.AuthUser;
import com.bits.language.resource.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppServiceHelper {

	private final PasswordEncoder bcryptEncoder;


	public AppServiceHelper(PasswordEncoder bcryptEncoder) {
		this.bcryptEncoder = bcryptEncoder;
	}

	public User buildUserEntity(String id, RegisterRequest req) {
		return new User().id(id).bio(req.getBio()).dateOfBirth(req.getDateOfBirth()).firstName(req.getFirstName())
				.gender(req.getGender()).lastName(req.getLastName()).contactNumber(req.getContactNumber())
				.username(req.getUsername());
	}

	public boolean bcryptMatches(String rawString, String encString) {
		return bcryptEncoder.matches(rawString, encString);
	}

	public String encodeString(String str) {
		return bcryptEncoder.encode(str);
	}

	public AuthUser buildAuthUser(String id, RegisterRequest req) {
		AuthUser user = new AuthUser();
		user.setEmail(req.getEmail());
		user.setId(id);
		user.setPassword(encodeString(req.getPassword()));
		user.setSecurityAnswer(encodeString(req.getSecurityAnswer().toLowerCase()));
		user.setSecurityQn(req.getSecurityQn());
		user.setUsername(req.getUsername());
		return user;
	}


	public AuthUserDto buildAuthUserDto(AuthUser authUser) {
		if (authUser == null)
			return null;
		final AuthUserDto dto = new AuthUserDto();
		dto.setPassword(authUser.getPassword());
		dto.setSecurityAnswer(authUser.getSecurityAnswer());
		dto.setUsername(authUser.getUsername());
		dto.setSecurityQn(authUser.getSecurityQn());
		dto.setEmail(authUser.getEmail());
		return dto;
	}

	public void updateAuthUser(AuthUser authUser, AuthUserDto authUserDto) {
		authUser.setPassword(authUserDto.getPassword());
		authUser.setSecurityAnswer(authUserDto.getSecurityAnswer());
		authUser.setSecurityQn(authUserDto.getSecurityQn());
		authUser.setEmail(authUserDto.getEmail());
	}

}
