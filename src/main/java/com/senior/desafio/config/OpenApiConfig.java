package com.senior.desafio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Desafio Senior")
                        .version("v1")
                        .description("")
                        .termsOfService("aderismartins.f@gmail.com")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("aderismartins.f@gmail.com")));
    }
}