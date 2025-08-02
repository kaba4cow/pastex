package com.kaba4cow.pastex.domain.paste.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.dto.PasteMapper;
import com.kaba4cow.pastex.domain.paste.model.Paste;
import com.kaba4cow.pastex.domain.paste.policy.PasteAccessPolicy;
import com.kaba4cow.pastex.domain.paste.repository.PasteRepository;
import com.kaba4cow.pastex.domain.user.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultReadPasteService implements ReadPasteService {

	private final PasteRepository pasteRepository;

	private final PasteAccessPolicy pasteAccessPolicy;

	private final PasteMapper pasteMapper;

	@Override
	public PasteDto getPaste(UUID id, String password, User requester) {
		Paste paste = pasteRepository.findByIdOrThrow(id);
		pasteAccessPolicy.checkAccess(paste, password, requester);
		return pasteMapper.mapToDto(paste);
	}

}
