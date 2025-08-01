package com.kaba4cow.pastex.common.validation;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExpirationValidator implements ConstraintValidator<ValidExpiration, String> {

	private static final Pattern PATTERN = Pattern.compile("^(\\d+)(m|h|d|mo|y)$");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (Objects.isNull(value) || value.isBlank())
			return true;
		Matcher matcher = PATTERN.matcher(value);
		if (!matcher.matches())
			return false;
		try {
			return Integer.parseInt(matcher.group(1)) > 0;
		} catch (NumberFormatException exception) {
			return false;
		}
	}

}
