package com.kaba4cow.pastex.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.kaba4cow.pastex.domain.user.dto.ChangeEmailRequest;
import com.kaba4cow.pastex.domain.user.dto.ChangePasswordRequest;
import com.kaba4cow.pastex.domain.user.dto.ProfileDto;
import com.kaba4cow.pastex.domain.user.model.User;
import com.kaba4cow.pastex.domain.user.service.ProfileReadService;
import com.kaba4cow.pastex.domain.user.service.ProfileUpdateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProfileControllerImpl implements ProfileController {

	private final ProfileReadService profileReadService;

	private final ProfileUpdateService profileUpdateService;

	@Override
	public ResponseEntity<ProfileDto> getUserInfo(User user) {
		return ResponseEntity.ok(profileReadService.getProfile(user));
	}

	@Override
	public ResponseEntity<ProfileDto> changeEmail(ChangeEmailRequest request, User user) {
		ProfileDto result = profileUpdateService.changeEmail(request, user);
		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<Void> changePassword(ChangePasswordRequest request, User user) {
		profileUpdateService.changePassword(request, user);
		return ResponseEntity.noContent().build();
	}

}
