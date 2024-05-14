package com.bits.language.auth.service;

import com.bits.language.auth.exception.EmailAlreadyExistsRuntimeException;
import com.bits.language.auth.exception.IncorrectPasswordRuntimeException;
import com.bits.language.auth.exception.IncorrectSecurityAnswerRuntimeException;
import com.bits.language.auth.exception.UsernameTakenRuntimeException;
import com.bits.language.auth.model.EmailLoginRequest;
import com.bits.language.auth.model.ForgotPasswordRequest;
import com.bits.language.auth.model.UsernameLoginRequest;
import com.bits.language.commons.exception.NoSuchUserRuntimeException;
import com.bits.language.commons.model.RegisterRequest;
import com.bits.language.commons.model.TokenResponse;

public interface AuthService {

	public TokenResponse registerUser(RegisterRequest registerRequest)
			throws UsernameTakenRuntimeException, EmailAlreadyExistsRuntimeException;

	public TokenResponse loginWithUsername(UsernameLoginRequest loginRequest)
			throws NoSuchUserRuntimeException, IncorrectPasswordRuntimeException;

	public String fetchSecurityQn(String username) throws NoSuchUserRuntimeException;

	public boolean forgotPassword(String username, ForgotPasswordRequest forgotPasswordRequest)
			throws NoSuchUserRuntimeException, IncorrectSecurityAnswerRuntimeException;

	public TokenResponse loginWithEmail(EmailLoginRequest request)
			throws IncorrectPasswordRuntimeException, NoSuchUserRuntimeException;

}
