package com.bits.language.commons.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jms-logger")
public class JMSLoggerProperty {
	
	private String loggerQueueName;

	public String getLoggerQueueName() {
		return loggerQueueName;
	}

	public void setLoggerQueueName(String loggerQueueName) {
		this.loggerQueueName = loggerQueueName;
	}	

}
