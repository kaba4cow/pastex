package com.kaba4cow.pastex.domain.paste.policy;

import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DefaultExpirationPolicy implements ExpirationPolicy {

	private final Pattern pattern = Pattern.compile("^(\\d+)(m|h|d|mo|y)$");

	private final Map<ChronoUnit, Long> limits = Map.of(//
			ChronoUnit.MINUTES, 60L * 24L, //
			ChronoUnit.HOURS, 24L * 7L, //
			ChronoUnit.DAYS, 365L, //
			ChronoUnit.MONTHS, 12L, //
			ChronoUnit.YEARS, 10L//
	);

	private final Map<String, ChronoUnit> mappings = Map.of(//
			"m", ChronoUnit.MINUTES, //
			"h", ChronoUnit.HOURS, //
			"d", ChronoUnit.DAYS, //
			"mo", ChronoUnit.MONTHS, //
			"y", ChronoUnit.YEARS//
	);

	@Override
	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public Map<ChronoUnit, Long> getUnitLimits() {
		return limits;
	}

	@Override
	public Map<String, ChronoUnit> getUnitMappings() {
		return mappings;
	}

}
