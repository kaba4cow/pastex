package com.kaba4cow.pastex.common.validation;

import org.springframework.stereotype.Component;

import com.kaba4cow.pastex.domain.paste.service.ExpirationParseException;
import com.kaba4cow.pastex.domain.paste.service.ExpirationService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ExpirationValidator implements ConstraintValidator<ValidExpiration, String> {

	private final ExpirationService expirationService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			expirationService.parseExpiration(value);
			return true;
		} catch (ExpirationParseException exception) {
			return false;
		}
	}

}
