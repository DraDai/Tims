package com.tims.common.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码编码器配置类
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * 返回一个BCryptPasswordEncoder对象,用来对密码进行编码和匹配验证
     * @return BCryptPasswordEncoder对象
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
