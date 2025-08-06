package com.tims.common.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;

@SpringBootApplication
public class TimsCommonSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimsCommonSecurityApplication.class, args);
    }
}
