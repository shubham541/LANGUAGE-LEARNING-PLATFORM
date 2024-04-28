package com.bits.language.commons.annotation.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bits.language.commons.annotation.UsernameValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBootConfiguration
class UsernameValidatorTest {

	UsernameValidator validator = new UsernameValidator();

	@Test
	void isValidTest() {
		String validUsername = "Abcs123";
		assertTrue(validator.isValid(validUsername, null));
	}

	@Test
	void isValidNullTest() {
		String inValidUsername = null;
		assertFalse(validator.isValid(inValidUsername, null));
	}

	@Test
	void isValidTest_OverLength() {
		String inValidUsername = "abcsdefgh122334";
		assertFalse(validator.isValid(inValidUsername, null));
	}

	@Test
	void isValidTest_UnderLength() {
		String inValidUsername = "j34     ";
		assertFalse(validator.isValid(inValidUsername, null));
	}

}
