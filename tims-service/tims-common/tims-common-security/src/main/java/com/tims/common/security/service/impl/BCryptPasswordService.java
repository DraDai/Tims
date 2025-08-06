package com.tims.common.security.service.impl;

import com.tims.common.security.service.PasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 密码处理服务,使用了Spring Security的密码处理类BCryptPasswordEncoder
 */
@Service
public class BCryptPasswordService implements PasswordService {
    private final PasswordEncoder passwordEncoder;

    public BCryptPasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 对密码进行编码,默认密码强度10
     * @param rawPassword 原始密码
     * @return 加密密码
     */
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 对密码进行验证
     * @param rawPassword 原始密码
     * @param encodedPassword 加密密码
     * @return 验证结果
     */
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
