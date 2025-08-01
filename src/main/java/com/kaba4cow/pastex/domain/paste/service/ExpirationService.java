package com.kaba4cow.pastex.domain.paste.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public interface ExpirationService {

	Duration parseExpiration(String expiration);

	default LocalDateTime computeExpiresAt(String expiration) {
		return Objects.isNull(expiration)//
				? LocalDateTime.MAX//
				: LocalDateTime.now().plus(parseExpiration(expiration));
	}

}
