package com.kaba4cow.pastex.domain.paste.policy;

import java.util.Map;
import java.util.regex.Pattern;

public interface ExpirationPolicy {

	Pattern getPattern();

	Map<String, Long> getUnitLimits();

	default boolean supportsUnit(String unit) {
		return getUnitLimits().containsKey(unit);
	}

	default long getLimit(String unit) {
		return getUnitLimits().get(unit);
	}

}
