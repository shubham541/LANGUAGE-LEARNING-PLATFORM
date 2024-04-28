package com.bits.language.commons.exception.aop;

import com.bits.language.commons.config.IAuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bits.language.commons.utility.AppUtility;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	private final AppUtility utility;

	private final Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

	private final IAuthenticationFacade authFacade;

	public GlobalControllerAdvice(AppUtility utility, IAuthenticationFacade authFacade) {
		this.utility = utility;
		this.authFacade = authFacade;
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.info("handleExceptionInternal: user {}", authFacade.getCurrentUser());
		return utility.handleControllerException(ex, status);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAllUncaughtException(RuntimeException exception, WebRequest request) {
		log.info("handleAllUncaughtException: user {} -> {}", authFacade.getCurrentUser(), exception.getMessage());
		return utility.handleControllerException(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
