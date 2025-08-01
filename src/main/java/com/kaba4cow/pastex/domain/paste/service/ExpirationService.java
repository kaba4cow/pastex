package com.kaba4cow.pastex.domain.paste.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public interface ExpirationService {

	Duration parseExpiration(String expiration);

	default LocalDateTime computeExpiresAt(String expiration) {
		LocalDateTime now = LocalDateTime.now();
		return Objects.isNull(expiration)//
				? LocalDateTime.MAX//
				: now.plus(parseExpiration(expiration));
	}

}
