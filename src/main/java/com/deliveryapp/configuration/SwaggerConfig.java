package com.deliveryapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * This class contains the information for setting up the details of the swagger
 * ui for show casing the API information
 * 
 * @author mohd.shadab
 */

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI grouOpenAPI() {
		return new OpenAPI().info(new Info().title("Delivery App Api").version("1.0.0")
				.description("This contains all the api information")
				.license(new License().name("Api 1.0").url("https://springdoc.org/v2/")));
	}
}
