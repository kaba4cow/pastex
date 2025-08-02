package com.kaba4cow.pastex.domain.user.service;

import com.kaba4cow.pastex.domain.user.dto.ChangeEmailRequest;
import com.kaba4cow.pastex.domain.user.dto.ChangePasswordRequest;
import com.kaba4cow.pastex.domain.user.dto.ProfileDto;
import com.kaba4cow.pastex.domain.user.model.User;

public interface ProfileUpdateService {

	ProfileDto changeEmail(ChangeEmailRequest request, User user);

	void changePassword(ChangePasswordRequest request, User user);

}
