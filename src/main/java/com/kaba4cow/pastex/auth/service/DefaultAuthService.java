package com.kaba4cow.pastex.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.auth.dto.AuthDto;
import com.kaba4cow.pastex.auth.dto.AuthRequest;
import com.kaba4cow.pastex.auth.dto.RegisterRequest;
import com.kaba4cow.pastex.domain.user.model.User;
import com.kaba4cow.pastex.domain.user.repository.UserRepository;
import com.kaba4cow.pastex.domain.user.service.UserValidationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultAuthService implements AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final UserValidationService userValidationService;

	@Override
	public void register(RegisterRequest request) {
		userValidationService.ensureEmailIsAvailable(request.getEmail());
		User saved = userRepository.save(User.builder()//
				.email(request.getEmail())//
				.passwordHash(passwordEncoder.encode(request.getPassword()))//
				.build());
		log.info("Registered user: {}", saved);
	}

	@Override
	public AuthDto authenticate(AuthRequest request) {
		User user = userRepository.findByEmailOrThrow(request.getEmail());
		userValidationService.ensurePasswordsMatch(request.getPassword(), user.getPasswordHash());
		String token = jwtService.generateToken(user);
		return new AuthDto(token);
	}

}
