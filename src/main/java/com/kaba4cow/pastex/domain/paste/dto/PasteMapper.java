package com.kaba4cow.pastex.domain.paste.dto;

import org.springframework.stereotype.Component;

import com.kaba4cow.pastex.domain.paste.Paste;

@Component
public class PasteMapper {

	public PasteDto mapToDto(Paste paste) {
		return PasteDto.builder()//
				.id(paste.getId())//
				.createdAt(paste.getCreatedAt())//
				.build();
	}

}
