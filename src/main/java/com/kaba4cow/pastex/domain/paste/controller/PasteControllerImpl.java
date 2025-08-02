package com.kaba4cow.pastex.domain.paste.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.paste.service.CreatePasteService;
import com.kaba4cow.pastex.domain.paste.service.ReadPasteService;
import com.kaba4cow.pastex.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PasteControllerImpl implements PasteController {

	private final CreatePasteService createPasteService;

	private final ReadPasteService readPasteService;

	@Override
	public ResponseEntity<PasteDto> createPaste(PasteCreateRequest request, User author) {
		return ResponseEntity.ok(createPasteService.createPaste(request, author));
	}

	@Override
	public ResponseEntity<PasteDto> getPaste(UUID id, String password, User requester) {
		return ResponseEntity.ok(readPasteService.getPaste(id, password, requester));
	}

}
