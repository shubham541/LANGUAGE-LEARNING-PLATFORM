package com.bits.language.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "MQException")
public class MQRuntimeException extends AppRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String message;

	public MQRuntimeException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

}
