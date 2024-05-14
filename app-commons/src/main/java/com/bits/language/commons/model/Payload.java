package com.bits.language.commons.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(value = Include.NON_NULL)
@JsonPropertyOrder({ "timestamp", "status", "message", "data", "error" })
public class Payload<T> {

	private String timestamp;

	private Integer status;

	private String message;

	private T data;

	private Object error;

	public static <T> Payload<T> ok(HttpStatus status, T data) {
		Payload<T> payload = new Payload<>();
		payload.setData(data);
		payload.setCommon(status);
		return payload;
	}

	public static <T> Payload<T> error(HttpStatus status, Object error) {
		Payload<T> payload = new Payload<>();
		payload.setError(error);
		payload.setCommon(status);
		return payload;
	}

	private void setCommon(HttpStatus status) {
		this.status = status.value();
		this.message = status.getReasonPhrase();
		this.timestamp = LocalDateTime.now().toString();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public String getTimestamp() {
		return timestamp;
	}

}
