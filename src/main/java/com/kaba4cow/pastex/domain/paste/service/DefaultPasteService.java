package com.kaba4cow.pastex.domain.paste.service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.dto.PasteMapper;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.paste.repository.PasteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultPasteService implements PasteService {

	private final PasteRepository pasteRepository;

	private final ExpirationService expirationService;

	private final PasswordEncoder passwordEncoder;

	private final PasteMapper pasteMapper;

	@Override
	public PasteDto createPaste(PasteCreateRequest request) {
		LocalDateTime expiresAt = expirationService.computeExpiresAt(request.getExpiration());
		Paste paste = Paste.builder()//
				.content(request.getContent())//
				.passwordHash(encodePasswordIfProvided(request.getPassword()))//
				.expiresAt(expiresAt)//
				.build();
		Paste saved = pasteRepository.save(paste);
		log.info("Created paste: {}", saved);
		return pasteMapper.mapToDto(saved);
	}

	private String encodePasswordIfProvided(String password) {
		return Objects.isNull(password)//
				? null//
				: passwordEncoder.encode(password);
	}

	@Override
	public PasteDto getPaste(UUID id) {
		return pasteMapper.mapToDto(pasteRepository.findByIdOrThrow(id));
	}

}
