package com.kaba4cow.pastex.domain.paste.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultExpirationService implements ExpirationService {

	@Override
	public LocalDateTime computeExpiresAt(Long expiration) {
		LocalDateTime now = LocalDateTime.now();
		return Objects.isNull(expiration)//
				? LocalDateTime.MAX//
				: now.plusMinutes(expiration);
	}

}
