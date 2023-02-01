package com.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
		info = @Info(
				title = "Rest Clinica",
				description = "End Point para el manejo de citas y usuarios en una clinica",
				version = "0.1",
				contact = @Contact(
						name = "Arcelio Montezuma",
						email = "arceliomonte15@gmail.com"
				)
		)
)
public class OpenApiConfig {

	@Bean
	public OpenAPI openAPI(){

		final String securityScheme="bearerAuth";
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement()
						.addList(securityScheme))
				.components(new Components()
						.addSecuritySchemes(securityScheme,new SecurityScheme()
								.name(securityScheme)
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")));
	}
}
