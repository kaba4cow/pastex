package com.kaba4cow.pastex.domain.paste.factory;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DefaultPasteFactory implements PasteFactory {

	private final PasswordEncoder passwordEncoder;

	@Override
	public Paste createPaste(PasteCreateRequest request, User author) {
		return Paste.builder()//
				.content(request.getContent())//
				.author(author)//
				.passwordHash(encodePasswordIfProvided(request.getPassword()))//
				.expiresAt(computeExpiresAt(request.getExpiration()))//
				.build();
	}

	private String encodePasswordIfProvided(String password) {
		return Objects.isNull(password) ? null : passwordEncoder.encode(password);
	}

	private LocalDateTime computeExpiresAt(Long expiration) {
		LocalDateTime now = LocalDateTime.now();
		return Objects.isNull(expiration)//
				? LocalDateTime.MAX//
				: now.plusMinutes(expiration);
	}

}
