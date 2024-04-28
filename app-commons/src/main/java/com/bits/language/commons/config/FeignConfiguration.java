package com.bits.language.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import feign.Logger;
import feign.RequestInterceptor;

@Configuration
public class FeignConfiguration {

	@Bean
	public RequestInterceptor requestInterceptor(IAuthenticationFacade auFacade) {
		return template -> template.header(HttpHeaders.AUTHORIZATION, auFacade.getAuthHeader());

	}
	
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.BASIC;
	}
}