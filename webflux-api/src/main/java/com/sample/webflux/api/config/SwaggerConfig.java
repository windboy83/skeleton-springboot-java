package com.sample.webflux.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("sample")
                        .version("v0.1")
                        .description("sample webflux API")
                );
    }
}
