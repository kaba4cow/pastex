package com.kaba4cow.pastex.domain.user.service;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.user.dto.ChangeEmailRequest;
import com.kaba4cow.pastex.domain.user.dto.ChangePasswordRequest;
import com.kaba4cow.pastex.domain.user.dto.ProfileDto;
import com.kaba4cow.pastex.domain.user.dto.ProfileMapper;
import com.kaba4cow.pastex.domain.user.model.User;
import com.kaba4cow.pastex.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultProfileUpdateService implements ProfileUpdateService {

	private final UserRepository userRepository;

	private final UserValidationService userValidationService;

	private final PasswordEncoder passwordEncoder;

	private final ProfileMapper profileMapper;

	@Override
	public ProfileDto changeEmail(ChangeEmailRequest request, User user) {
		String oldEmail = user.getEmail();
		String newEmail = request.getEmail();
		if (!Objects.equals(newEmail, oldEmail)) {
			userValidationService.ensureEmailIsAvailable(newEmail);
			user.setEmail(newEmail);
			log.info("User {} changed email: {} -> {}", user.getId(), oldEmail, newEmail);
			return profileMapper.mapToDto(userRepository.save(user));
		} else
			return profileMapper.mapToDto(user);
	}

	@Override
	public void changePassword(ChangePasswordRequest request, User user) {
		userValidationService.ensurePasswordsMatch(request.getOldPassword(), user.getPasswordHash());
		user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
		log.info("User {} changed password", user.getId());
		userRepository.save(user);
	}

}
