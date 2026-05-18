package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Value("${cors.allowed.origins}")          
    private String allowedOrigins;   

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // CORS policy applies to all paths (/**)
                registry.addMapping("/**")
                        // Allow only specific origins (e.g., your frontend at localhost:3000)
                        .allowedOrigins(allowedOrigins.split(","))
                        // Allow specific HTTP methods (GET, POST, etc.)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Allow specific headers (or all headers using "*")
                        .allowedHeaders("*")
                        // Allow credentials like cookies, authentication headers, etc.
                        .allowCredentials(true);
            }
        };
    }
}

