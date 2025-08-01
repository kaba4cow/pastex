package com.kaba4cow.pastex.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kaba4cow.pastex.auth.dto.AuthDto;
import com.kaba4cow.pastex.auth.dto.AuthRequest;
import com.kaba4cow.pastex.auth.dto.RegisterRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(//
		name = "Authentication", //
		description = """
				User registration and login endpoints.
				"""//
)
@RequestMapping("/api/auth")
public interface AuthController {

	@Operation(//
			summary = "Register new user", //
			description = """
					Creates a new user account.
					"""//
	)
	@PostMapping("/register")
	ResponseEntity<Void> registerUser(//
			@Valid @RequestBody RegisterRequest request//
	);

	@Operation(//
			summary = "Authenticate user and return token", //
			description = """
					Authenticates the provided credentials and returns a JWT token.
					"""//
	)
	@PostMapping("/login")
	ResponseEntity<AuthDto> authenticateUser(//
			@Valid @RequestBody AuthRequest request//
	);

}
