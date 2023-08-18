package com.skyeng.test.skyengtesttask.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPI() {

        Info info = new Info()
                .title("Skyeng test task API")
                .version("0.1")
                .description("This API exposes endpoints to manage test task.");

        return new OpenAPI().info(info);
    }
}