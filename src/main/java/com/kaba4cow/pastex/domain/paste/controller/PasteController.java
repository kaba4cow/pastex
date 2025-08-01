package com.kaba4cow.pastex.domain.paste.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kaba4cow.pastex.auth.annotation.CurrentUser;
import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.user.model.User;

@RequestMapping("/api/pastes")
public interface PasteController {

	@PostMapping
	ResponseEntity<PasteDto> createPaste(//
			@RequestBody PasteCreateRequest request, //
			@CurrentUser User author//
	);

	@GetMapping("/{id}")
	ResponseEntity<PasteDto> getPaste(//
			@PathVariable UUID id, //
			@RequestParam(required = false) String password, //
			@CurrentUser User requester//
	);

}
