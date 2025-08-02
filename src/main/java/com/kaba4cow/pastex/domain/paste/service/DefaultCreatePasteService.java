package com.kaba4cow.pastex.domain.paste.service;

import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.dto.PasteMapper;
import com.kaba4cow.pastex.domain.paste.factory.PasteFactory;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.paste.repository.PasteRepository;
import com.kaba4cow.pastex.domain.user.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultCreatePasteService implements CreatePasteService {

	private final PasteRepository pasteRepository;

	private final PasteFactory pasteFactory;

	private final PasteMapper pasteMapper;

	@Override
	public PasteDto createPaste(PasteCreateRequest request, User author) {
		Paste saved = pasteRepository.save(pasteFactory.createPaste(request, author));
		log.info("Created paste: {}", saved);
		return pasteMapper.mapToDto(saved);
	}

}
