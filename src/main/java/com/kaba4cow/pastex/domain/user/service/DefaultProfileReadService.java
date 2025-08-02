package com.kaba4cow.pastex.domain.user.service;

import org.springframework.stereotype.Service;

import com.kaba4cow.pastex.domain.user.dto.ProfileDto;
import com.kaba4cow.pastex.domain.user.dto.ProfileMapper;
import com.kaba4cow.pastex.domain.user.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultProfileReadService implements ProfileReadService {

	private final ProfileMapper profileMapper;

	@Override
	public ProfileDto getProfile(User user) {
		return profileMapper.mapToDto(user);
	}

}
