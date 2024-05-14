package com.bits.language.auth.exception;

import com.bits.language.commons.constant.AppConstants;
import com.bits.language.commons.exception.AppRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = AppConstants.INCORRECT_PASSWORD_WARNING)
public class IncorrectPasswordRuntimeException extends AppRuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String toString() {
        return AppConstants.INCORRECT_PASSWORD_WARNING;
    }

}
