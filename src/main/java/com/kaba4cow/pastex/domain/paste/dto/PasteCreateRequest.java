package com.kaba4cow.pastex.domain.paste.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PasteCreateRequest {

	@NotBlank(message = "Content must not be blank")
	private String content;

}
