package com.apae.eventos.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "application.security.jwt")
@Data
public class JwtProperties {
    private String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private long tokenExpirationMs = 86400000; // 1 dia
    private String tokenPrefix = "Bearer ";
    private String headerString = "Authorization";
}