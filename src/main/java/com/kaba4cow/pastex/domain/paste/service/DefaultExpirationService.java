package com.kaba4cow.pastex.domain.paste.service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.paste.policy.ExpirationPolicy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultExpirationService implements ExpirationService {

	private final ExpirationPolicy expirationPolicy;

	@Override
	public Duration parseExpiration(String expiration) {
		if (isBlank(expiration))
			return null;
		Matcher matcher = getMatcher(expiration);
		if (!matcher.matches())
			throw new ExpirationParseException("Invalid expiration format");
		ChronoUnit unit = extractUnit(matcher);
		try {
			long amount = extractAmount(matcher);
			validateAmount(amount, unit);
			return Duration.of(amount, unit);
		} catch (NumberFormatException exception) {
			throw new ExpirationParseException("Invalid amount format", exception);
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

	private ChronoUnit extractUnit(Matcher matcher) {
		String raw = matcher.group(2);
		if (!expirationPolicy.supportsUnit(raw))
			throw new ExpirationParseException("Unsupported unit: %s".formatted(raw));
		return expirationPolicy.getChronoUnit(raw);
	}

	private void validateAmount(long amount, ChronoUnit unit) {
		if (amount <= 0L)
			throw new ExpirationParseException("Amount must be positive: %s <= 0".formatted(amount));
		long limit = expirationPolicy.getLimit(unit);
		if (amount > limit)
			throw new ExpirationParseException("Amount of out allowed range: %s > %s".formatted(amount, limit));
	}

}
