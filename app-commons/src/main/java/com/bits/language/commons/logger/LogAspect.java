package com.bits.language.commons.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LogAspect {

	private Logger log = LoggerFactory.getLogger(LogAspect.class);

	private final ObjectMapper mapper = new ObjectMapper();

	@Around(value = "@annotation(com.bits.language.commons.logger.LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		final String shortSign = joinPoint.getSignature().toShortString();
		if (log.isInfoEnabled()) {
			String logMessage = String.format("START: %s", shortSign);
			log.info(logMessage);
		}
		long start = System.currentTimeMillis();
		logArgs(joinPoint.getArgs());
		Object proceed = joinPoint.proceed();

		long executionTime = System.currentTimeMillis() - start;
		if (log.isInfoEnabled()) {
			String logMessage = String.format("END: %s executed in %s ms", shortSign, executionTime);
			log.info(logMessage);
		}
		return proceed;
	}

	private void logArgs(Object[] args) {
		if (log.isDebugEnabled() && args != null) {
			for (int i = 0; i < args.length; i++) {
				Object object = args[i];
				try {
					log.debug(object == null ? "null" : mapper.writeValueAsString(object));
				} catch (JsonProcessingException e) {
					log.debug("Couldn't parse {}", object);
				}
			}
		}
	}

}
