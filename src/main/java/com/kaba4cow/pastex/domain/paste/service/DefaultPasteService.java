package com.kaba4cow.pastex.domain.paste.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.dto.PasteMapper;
import com.kaba4cow.pastex.domain.paste.factory.PasteFactory;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.paste.policy.PasteAccessPolicy;
import com.kaba4cow.pastex.domain.paste.repository.PasteRepository;
import com.kaba4cow.pastex.domain.user.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultPasteService implements PasteService {

	private final PasteRepository pasteRepository;

	private final PasteFactory pasteFactory;

	private final PasteAccessPolicy pasteAccessPolicy;

	private final PasteMapper pasteMapper;

	@Override
	public PasteDto createPaste(PasteCreateRequest request, User author) {
		Paste saved = pasteRepository.save(pasteFactory.createPaste(request, author));
		log.info("Created paste: {}", saved);
		return pasteMapper.mapToDto(saved);
	}

	@Override
	public PasteDto getPaste(UUID id, String password, User requester) {
		Paste paste = pasteRepository.findByIdOrThrow(id);
		pasteAccessPolicy.checkAccess(paste, password, requester);
		return pasteMapper.mapToDto(paste);
	}

}
