package com.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
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

}
