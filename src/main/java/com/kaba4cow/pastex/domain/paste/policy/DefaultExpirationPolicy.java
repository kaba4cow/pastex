package com.kaba4cow.pastex.domain.paste.policy;

import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DefaultExpirationPolicy implements ExpirationPolicy {

	private final Pattern pattern = Pattern.compile("^(\\d+)(m|h|d|mo|y)$");

	private final Map<String, Long> limits = Map.of(//
			"m", 60L * 24L, //
			"h", 24L * 7L, //
			"d", 365L, //
			"mo", 12L, //
			"y", 10L//
	);

	@Override
	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public Map<String, Long> getUnitLimits() {
		return limits;
	}

}
