package com.kaba4cow.pastex.domain.paste.policy;

import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.regex.Pattern;

public interface ExpirationPolicy {

	Pattern getPattern();

	Map<ChronoUnit, Long> getUnitLimits();

	Map<String, ChronoUnit> getUnitMappings();

	default boolean supportsUnit(String unit) {
		return getUnitMappings().containsKey(unit);
	}

	default long getLimit(ChronoUnit unit) {
		return getUnitLimits().get(unit);
	}

	default ChronoUnit getChronoUnit(String unit) {
		return getUnitMappings().get(unit);
	}

}
