package com.kaba4cow.pastex.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.kaba4cow.pastex.auth.dto.AuthDto;
import com.kaba4cow.pastex.auth.dto.AuthRequest;
import com.kaba4cow.pastex.auth.dto.RegisterRequest;
import com.kaba4cow.pastex.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController {

	private final AuthService authService;

	@Override
	public ResponseEntity<Void> registerUser(RegisterRequest request) {
		authService.register(request);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<AuthDto> authenticateUser(AuthRequest request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

}
