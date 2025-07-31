package com.kaba4cow.pastex.domain.paste.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.paste.Paste;
import com.kaba4cow.pastex.domain.paste.PasteRepository;
import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.dto.PasteMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultPasteService implements PasteService {

	private final PasteRepository pasteRepository;

	private final PasteMapper pasteMapper;

	@Override
	public PasteDto createPaste(PasteCreateRequest request) {
		Paste paste = Paste.builder()//
				.content(request.getContent())//
				.build();
		Paste saved = pasteRepository.save(paste);
		log.info("Created paste: {}", saved);
		return pasteMapper.mapToDto(saved);
	}

	@Override
	public PasteDto getPaste(UUID id) {
		return pasteMapper.mapToDto(pasteRepository.findByIdOrThrow(id));
	}

}
