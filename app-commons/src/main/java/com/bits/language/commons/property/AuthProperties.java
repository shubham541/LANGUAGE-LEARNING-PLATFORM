package com.bits.language.commons.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("auth-config")
public class AuthProperties {

	private String skipPath;

	public String getSkipPath() {
		return skipPath;
	}

	public void setSkipPath(String skipPath) {
		this.skipPath = skipPath;
	}

}
