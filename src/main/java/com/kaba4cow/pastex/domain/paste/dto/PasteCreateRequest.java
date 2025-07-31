package com.kaba4cow.pastex.domain.paste.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasteCreateRequest {

	@NotBlank(message = "Content must not be blank")
	private String content;

}
