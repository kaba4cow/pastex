package com.kaba4cow.pastex.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.common.exception.ConflictException;
import com.kaba4cow.pastex.common.exception.WrongPasswordException;
import com.kaba4cow.pastex.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefaultUserValidationService implements UserValidationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public void ensureEmailIsAvailable(String email) {
		if (userRepository.existsByEmail(email))
			throw new ConflictException("Email is taken: %s".formatted(email));
	}

	@Override
	public void ensurePasswordsMatch(String password, String passwordHash) {
		if (!passwordEncoder.matches(password, passwordHash))
			throw new WrongPasswordException("Wrong password");
	}

}
