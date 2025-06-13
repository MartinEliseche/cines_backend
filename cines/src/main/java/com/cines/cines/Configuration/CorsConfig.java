package com.cines.cines.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        if (registry == null) {
            throw new IllegalArgumentException("CorsRegistry No Puede Ser Nulo");
        }

        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173");
    }
}
