package com.kaba4cow.pastex.domain.user.dto;

import org.springframework.stereotype.Component;

import com.kaba4cow.pastex.domain.user.model.User;

@Component
public class ProfileMapper {

	public ProfileDto mapToDto(User user) {
		return ProfileDto.builder()//
				.email(user.getEmail())//
				.build();
	}

}
