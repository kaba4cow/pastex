package com.kaba4cow.pastex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()//
				.info(new Info()//
						.title("Pastex API")//
						.version("v1")//
						.description("RESTful pastebin application")//
				)//
				.addSecurityItem(new SecurityRequirement()//
						.addList("bearerAuth")//
				)//
				.components(new Components()//
						.addSecuritySchemes("bearerAuth", //
								new SecurityScheme()//
										.type(SecurityScheme.Type.HTTP)//
										.scheme("bearer")//
										.bearerFormat("JWT")//
						)//
				);
	}

}
