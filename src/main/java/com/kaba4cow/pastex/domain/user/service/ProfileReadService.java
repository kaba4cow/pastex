package com.kaba4cow.pastex.domain.user.service;

import com.kaba4cow.pastex.domain.user.dto.ProfileDto;
import com.kaba4cow.pastex.domain.user.model.User;

public interface ProfileReadService {

	ProfileDto getProfile(User user);

}
