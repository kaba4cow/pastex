package com.kaba4cow.pastex.domain.paste.dto;

import org.springframework.stereotype.Component;

import com.kaba4cow.pastex.domain.paste.model.Paste;

@Component
public class PasteMapper {

	public PasteDto mapToDto(Paste paste) {
		return PasteDto.builder()//
				.id(paste.getId())//
				.content(paste.getContent())//
				.createdAt(paste.getCreatedAt())//
				.build();
	}

}
