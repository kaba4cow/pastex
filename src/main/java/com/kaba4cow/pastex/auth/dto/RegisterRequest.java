package com.kaba4cow.pastex.auth.dto;

import com.kaba4cow.pastex.domain.user.validation.ValidEmail;
import com.kaba4cow.pastex.domain.user.validation.ValidPassword;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Request for registering a new user")
public class RegisterRequest {

	@NotBlank(message = "Email is required")
	@ValidEmail
	@Schema(//
			description = "User email", //
			example = "john@example.com"//
	)
	private String email;

	@NotBlank(message = "Password is required")
	@ValidPassword
	@Schema(//
			description = "Password", //
			example = "password1234"//
	)
	private String password;

}
