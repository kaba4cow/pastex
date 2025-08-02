package com.kaba4cow.pastex.domain.paste.service;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultPastePasswordService implements PastePasswordService {

	private final PasswordEncoder passwordEncoder;

	@Override
	public String encodePasswordIfProvided(String password) {
		return Objects.isNull(password) ? null : passwordEncoder.encode(password);
	}

}
