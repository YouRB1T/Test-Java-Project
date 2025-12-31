package org.zhkh.resident.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI residentServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Resident Service API")
                        .description("API для управления жильцами (Residents)")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Базы данных")
                        ));
    }
}
