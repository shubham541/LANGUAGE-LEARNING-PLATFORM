package com.bits.language.data.service.impl;

import javax.validation.Valid;

import com.bits.language.data.entity.AuthUser;
import com.bits.language.data.entity.User;
import com.bits.language.data.repo.UserRepo;
import com.bits.language.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.bits.language.commons.logger.LogExecutionTime;
import com.bits.language.commons.model.AuthUserDto;
import com.bits.language.commons.model.RegisterRequest;
import com.bits.language.commons.utility.AppUtility;
import com.bits.language.data.service.helper.AppServiceHelper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo authUserRepo;

	@Autowired
	private AppUtility appUtility;

	@Autowired
	private AppServiceHelper serviceHelper;

	@Autowired
	private MongoTemplate mongoTemplate;

	@LogExecutionTime
	@Override
	public AuthUserDto findByUsernameOrEmail(String username, String email) {
		final var authUser = authUserRepo.findByUsernameOrEmail(username, email);
		return serviceHelper.buildAuthUserDto(authUser);
	}

	@LogExecutionTime
	@Override
	public String registerUser(RegisterRequest request) {
		final String id = appUtility.generateStringId();
		authUserRepo.save(serviceHelper.buildAuthUser(id, request));
		final User user = mongoTemplate.save(serviceHelper.buildUserEntity(id, request));
		return user.getUsername();
	}

	@LogExecutionTime
	@Override
	public AuthUserDto findByUsername(String username) {
		final AuthUser authUser = authUserRepo.findByUsername(username);
		return serviceHelper.buildAuthUserDto(authUser);
	}

	@LogExecutionTime
	@Override
	public AuthUserDto updateAuthUser(@Valid AuthUserDto authUserDto) {
		final AuthUser authUser = authUserRepo.findByUsername(authUserDto.getUsername());
		serviceHelper.updateAuthUser(authUser, authUserDto);
		final AuthUser updated = authUserRepo.save(authUser);
		return serviceHelper.buildAuthUserDto(updated);
	}

	@LogExecutionTime
	@Override
	public AuthUserDto findByEmail(String email) {
		final AuthUser authUser = authUserRepo.findByEmail(email);
		return serviceHelper.buildAuthUserDto(authUser);
	}

}
