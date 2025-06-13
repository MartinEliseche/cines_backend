package com.cines.cines.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String[] ALLOWED_ORIGINS = {
        "http://localhost:5173",       
        "http://127.0.0.1:5173",       
        "http://localhost:3000"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (registry == null) {
            throw new IllegalArgumentException("CorsRegistry no puede ser nulo");
        }

        registry.addMapping("/**")
            .allowedOrigins(ALLOWED_ORIGINS)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD")
            .allowedHeaders("*")
            .exposedHeaders(
                "Authorization",
                "Content-Type",
                "Content-Disposition",
                "Content-Length",
                "X-Requested-With"
            )
            .allowCredentials(true)
            .maxAge(3600); 
    }
}