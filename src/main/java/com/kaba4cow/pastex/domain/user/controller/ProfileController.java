package com.kaba4cow.pastex.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kaba4cow.pastex.domain.user.dto.ChangeEmailRequest;
import com.kaba4cow.pastex.domain.user.dto.ChangePasswordRequest;
import com.kaba4cow.pastex.domain.user.dto.ProfileDto;
import com.kaba4cow.pastex.domain.user.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(//
		name = "User Profile", //
		description = """
				Endpoints for managing the authenticated user's profile.
				""")
@RequestMapping("/api/profile")
public interface ProfileController {

	@Operation(//
			summary = "Get current authenticated user", //
			description = """
					Returns information about the currently authenticated user.
					""")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/me")
	ResponseEntity<ProfileDto> getUserInfo(//
			@AuthenticationPrincipal User user//
	);

	@Operation(//
			summary = "Change email", //
			description = """
					Allows an authenticated user to change their email address.
					""")
	@PreAuthorize("isAuthenticated()")
	@PatchMapping("/email")
	ResponseEntity<ProfileDto> changeEmail(//
			@Valid @RequestBody ChangeEmailRequest request, //
			@AuthenticationPrincipal User user//
	);

	@Operation(//
			summary = "Change password", //
			description = """
					Allows an authenticated user to change their password.
					""")
	@PreAuthorize("isAuthenticated()")
	@PatchMapping("/password")
	ResponseEntity<Void> changePassword(//
			@Valid @RequestBody ChangePasswordRequest request, //
			@AuthenticationPrincipal User user//
	);

}
