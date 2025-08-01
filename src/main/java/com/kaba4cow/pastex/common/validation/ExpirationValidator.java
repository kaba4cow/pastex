package com.kaba4cow.pastex.common.validation;

import java.util.Objects;
import java.util.regex.Matcher;

import org.springframework.stereotype.Component;

import com.kaba4cow.pastex.domain.paste.policy.ExpirationPolicy;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ExpirationValidator implements ConstraintValidator<ValidExpiration, String> {

	private final ExpirationPolicy expirationPolicy;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (isBlank(value))
			return true;
		Matcher matcher = getMatcher(value);
		if (!matcher.matches())
			return false;
		String unit = extractUnit(matcher);
		if (!expirationPolicy.supportsUnit(unit))
			return false;
		try {
			long amount = extractAmount(matcher);
			long limit = expirationPolicy.getLimit(unit);
			return amount > 0L && amount <= limit;
		} catch (NumberFormatException exception) {
			return false;
		}
	}

	private boolean isBlank(String value) {
		return Objects.isNull(value) || value.isBlank();
	}

	private Matcher getMatcher(String value) {
		return expirationPolicy.getPattern().matcher(value);
	}

	private long extractAmount(Matcher matcher) {
		return Long.parseLong(matcher.group(1));
	}

	private String extractUnit(Matcher matcher) {
		return matcher.group(2);
	}

}
