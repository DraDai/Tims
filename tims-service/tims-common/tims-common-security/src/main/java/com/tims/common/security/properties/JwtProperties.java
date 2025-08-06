package com.tims.common.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tims.common.security.jwt")
public class JwtProperties {
    private String secret;
    private long expiration;
}
