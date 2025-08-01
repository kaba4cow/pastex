package com.kaba4cow.pastex.domain.paste.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kaba4cow.pastex.domain.paste.dto.PasteCreateRequest;
import com.kaba4cow.pastex.domain.paste.dto.PasteDto;
import com.kaba4cow.pastex.domain.user.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(//
		name = "Pastes", //
		description = """
				Endpoints for paste creation and retrieval.
				"""//
)
@RequestMapping("/api/pastes")
public interface PasteController {

	@Operation(//
			summary = "Create a new paste", //
			description = """
					Creates a new paste with optional password and returns the paste data.
					"""//
	)
	@PostMapping
	ResponseEntity<PasteDto> createPaste(//
			@RequestBody PasteCreateRequest request, //
			@AuthenticationPrincipal User author//
	);

	@Operation(//
			summary = "Get post by ID", //
			description = """
					Returns post by ID using password if paste is secured.
					"""//
	)
	@GetMapping("/{id}")
	ResponseEntity<PasteDto> getPaste(//
			@PathVariable UUID id, //
			@RequestParam(required = false) String password, //
			@AuthenticationPrincipal User requester//
	);

}
