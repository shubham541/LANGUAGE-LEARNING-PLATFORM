package com.bits.language.auth.service.impl;

import java.util.Optional;

import com.bits.language.auth.exception.EmailAlreadyExistsRuntimeException;
import com.bits.language.auth.exception.IncorrectPasswordRuntimeException;
import com.bits.language.auth.exception.IncorrectSecurityAnswerRuntimeException;
import com.bits.language.auth.exception.UsernameTakenRuntimeException;
import com.bits.language.auth.utility.AppJwtUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.language.auth.model.EmailLoginRequest;
import com.bits.language.auth.model.ForgotPasswordRequest;
import com.bits.language.auth.model.UsernameLoginRequest;
import com.bits.language.auth.service.AuthService;
import com.bits.language.commons.exception.NoSuchUserRuntimeException;
import com.bits.language.commons.feign.AuthUserFeign;
import com.bits.language.commons.logger.LogExecutionTime;
import com.bits.language.commons.model.AuthUserDto;
import com.bits.language.commons.model.RegisterRequest;
import com.bits.language.commons.model.TokenResponse;
import com.bits.language.commons.utility.AppUtility;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AppUtility appUtility;

	@Autowired
	private AuthUserFeign authUserFeign;

	@Autowired
	private AppJwtUtility jwtUtility;

	@LogExecutionTime
	@Override
	public TokenResponse registerUser(RegisterRequest registerRequest)
			throws UsernameTakenRuntimeException, EmailAlreadyExistsRuntimeException {
		final AuthUserDto existingUser = authUserFeign.findByUsernameOrEmail(registerRequest.getUsername(),
				registerRequest.getEmail());
		if (existingUser != null) {
			if (StringUtils.equals(registerRequest.getUsername(), existingUser.getUsername())) {
				throw new UsernameTakenRuntimeException();
			} else {
				throw new EmailAlreadyExistsRuntimeException();
			}
		}
		final String username = authUserFeign.register(registerRequest);
		return jwtUtility.buildTokenResponse(username);
	}

	@LogExecutionTime
	@Override
	public TokenResponse loginWithUsername(UsernameLoginRequest loginRequest)
			throws NoSuchUserRuntimeException, IncorrectPasswordRuntimeException {
		final AuthUserDto user = Optional.ofNullable(authUserFeign.findByUsername(loginRequest.getUsername()))
				.orElseThrow(NoSuchUserRuntimeException::new);

		return checkPasswordMatches(loginRequest.getPassword(), user);
	}

	@LogExecutionTime
	@Override
	public String fetchSecurityQn(String username) throws NoSuchUserRuntimeException {
		return Optional.ofNullable(authUserFeign.findByUsername(username)).orElseThrow(NoSuchUserRuntimeException::new)
				.getSecurityQn();
	}

	@LogExecutionTime
	@Override
	public boolean forgotPassword(String username, ForgotPasswordRequest forgotPasswordRequest)
			throws NoSuchUserRuntimeException, IncorrectSecurityAnswerRuntimeException {
		final AuthUserDto user = Optional.ofNullable(authUserFeign.findByUsername(username))
				.orElseThrow(NoSuchUserRuntimeException::new);
		if (!appUtility.bcryptMatches(forgotPasswordRequest.getSecurityAnswer().toLowerCase(),
				user.getSecurityAnswer())) {
			throw new IncorrectSecurityAnswerRuntimeException();
		}
		String newPassword = forgotPasswordRequest.getNewPassword();
		user.setPassword(appUtility.encodeString(newPassword));
		return appUtility.bcryptMatches(newPassword, authUserFeign.updateAuthUser(user).getPassword());
	}

	@Override
	public TokenResponse loginWithEmail(EmailLoginRequest request)
			throws IncorrectPasswordRuntimeException, NoSuchUserRuntimeException {
		final AuthUserDto user = Optional.ofNullable(authUserFeign.findByEmail(request.getEmail()))
				.orElseThrow(NoSuchUserRuntimeException::new);

		return checkPasswordMatches(request.getPassword(), user);
	}

	private TokenResponse checkPasswordMatches(String loginPassword, final AuthUserDto user)
			throws IncorrectPasswordRuntimeException {
		if (!appUtility.bcryptMatches(loginPassword, user.getPassword())) {
			throw new IncorrectPasswordRuntimeException();
		}
		return jwtUtility.buildTokenResponse(user.getUsername());
	}

}
