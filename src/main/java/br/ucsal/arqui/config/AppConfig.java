package br.ucsal.arqui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

public class AppConfig {
    @Configuration
    public class RestTemplateConfig {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}