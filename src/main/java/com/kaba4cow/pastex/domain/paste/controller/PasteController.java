package com.kaba4cow.pastex.domain.paste.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;

@RequestMapping("/api/pastes")
public interface PasteController {

	@PostMapping
	ResponseEntity<PasteDto> createPaste(//
			@RequestBody PasteCreateRequest request//
	);

	@GetMapping("/{id}")
	ResponseEntity<PasteDto> getPaste(//
			@PathVariable UUID id//
	);

}
