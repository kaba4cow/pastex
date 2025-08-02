package com.kaba4cow.pastex.domain.paste.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.dto.PasteMapper;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.paste.repository.PasteRepository;
import com.kaba4cow.pastex.domain.user.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultPasteCreateService implements PasteCreateService {

	private final PasteRepository pasteRepository;

	private final PasswordEncoder passwordEncoder;

	private final PasteMapper pasteMapper;

	@Override
	public PasteDto createPaste(PasteCreateRequest request, User author) {
		Paste paste = Paste.builder()//
				.content(request.getContent())//
				.author(author)//
				.passwordHash(encodePasswordIfProvided(request.getPassword()))//
				.expiresAt(computeExpiresAt(request.getExpiration()))//
				.build();
		Paste saved = pasteRepository.save(paste);
		log.info("Created paste: {}", saved);
		return pasteMapper.mapToDto(saved);
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
