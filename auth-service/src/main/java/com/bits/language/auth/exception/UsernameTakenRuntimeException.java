package com.bits.language.auth.exception;

import com.bits.language.commons.constant.AppConstants;
import com.bits.language.commons.exception.AppRuntimeException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = AppConstants.USERNAME_ALREADY_TAKEN)
public class UsernameTakenRuntimeException extends AppRuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
