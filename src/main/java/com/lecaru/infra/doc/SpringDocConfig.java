package com.lecaru.infra.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Lecaru API")
                        .description("Lecaru restaurant Rest API.")
                        .contact(new Contact()
                                .name("Caio Pfaltzgraff")
                                .email("lecaru.system@gmail.com")
                        )
                );
    }

}
