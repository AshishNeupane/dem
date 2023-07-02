package com.ann.dem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Profile("development")
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //registry.addMapping("/login").allowedMethods("POST").allowedOrigins("http://localhost:4200");
        //registry.addMapping("/master/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedOrigins("http://localhost:4200");

        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:4200")
        .allowedOrigins("http://localhost:4200","http://fhl.annofi.com:4200","http://npl.annofi.com:4200")
                .allowedMethods("POST","GET","PUT","DELETE");
    }
}