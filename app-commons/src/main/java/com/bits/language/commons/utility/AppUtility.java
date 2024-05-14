package com.bits.language.commons.utility;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.bits.language.commons.model.Payload;
import com.bits.language.commons.property.AuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableConfigurationProperties(AuthProperties.class)
public class AppUtility {

	private final PasswordEncoder bcryptEncoder;

	private final ObjectMapper mapper = new ObjectMapper();

	public AppUtility(PasswordEncoder bcryptEncoder) {
		this.bcryptEncoder = bcryptEncoder;
	}

	public boolean bcryptMatches(String rawString, String encString) {
		return bcryptEncoder.matches(rawString, encString);
	}

	public String encodeString(String str) {
		return bcryptEncoder.encode(str);
	}

	public ResponseEntity<Object> buildErrorResponseEntity(HttpStatus status, Object error) {
		return new ResponseEntity<>(buildErrorPayload(status, getMessage(error)), status);
	}

	public Payload<Object> buildErrorPayload(HttpStatus status, Object error) {
		return Payload.error(status, error);
	}

	public <T> Payload<T> buildOkPayload(HttpStatus status, T data) {
		return Payload.ok(status, data);
	}

	private Object getMessage(Object data) {
		if (data instanceof Iterable) {
			return ((Collection<?>) data).stream().map(this::mapObjectIterable).collect(Collectors.toList());
		}
		return data;
	}

	private String mapObjectIterable(Object data) {
		if (data instanceof FieldError) {
			FieldError fieldError = (FieldError) data;
			return fieldError.getField() + " " + fieldError.getDefaultMessage();
		} else {
			return data.toString();
		}
	}

	public String generateStringId() {
		return UUID.randomUUID().toString();
	}

	public <T> ResponseEntity<Payload<T>> buildSuccessEntity(HttpStatus status, T data) {
		return new ResponseEntity<>(buildOkPayload(status, data), status);
	}

	public <T> ResponseEntity<Payload<T>> buildSuccessEntity(T data) {
		final HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<>(buildOkPayload(status, data), status);
	}

	public ResponseEntity<Object> handleControllerException(Exception ex, HttpStatus status) {
		if (ex instanceof MethodArgumentNotValidException) {
			final MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
			return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, e.getAllErrors());
		}
		return buildErrorResponseEntity(status, ex.getMessage());
	}

	public void writeErrorResponse(HttpServletResponse response, Exception exception) throws IOException {
		byte[] content = mapper.writeValueAsBytes(buildErrorPayload(HttpStatus.UNAUTHORIZED, exception.getMessage()));
		response.getOutputStream().write(content);
	}
}
