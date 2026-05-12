package com.abdulmajid.expensetracker.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenApiConfig {

    @Bean
    public OpenAPI expenseTrackerOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Expense Tracker API")

                        .description(
                                "REST API for Expense Tracker Application"
                        )

                        .version("1.0")

                        .contact(new Contact()

                                .name("Abdul Majid")

                                .email("abdul@example.com")
                        )

                        .license(new License()

                                .name("Apache 2.0")
                        )
                )

                .externalDocs(new ExternalDocumentation()

                        .description("Project Documentation")
                );
    }
}