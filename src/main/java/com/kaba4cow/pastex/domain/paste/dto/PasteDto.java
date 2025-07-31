package com.kaba4cow.pastex.domain.paste.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PasteDto {

	private final UUID id;

	private final String content;

	private final LocalDateTime createdAt;

}
