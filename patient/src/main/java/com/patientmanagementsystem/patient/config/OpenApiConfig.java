package com.patientmanagementsystem.patient.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI patientServiceOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Patient Service API")
                        .description("CRUD API for managing patients")
                        .version("v1"));
    }
}
