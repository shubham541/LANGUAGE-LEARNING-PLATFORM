package com.bits.language.commons.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) return false;
		final int length = value.trim().length();
		return length <= 12 && length >= 5;
	}

}
