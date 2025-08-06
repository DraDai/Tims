package com.tims.common.security.service;

/**
 * 密码处理接口,要求对文本进行加密和匹配
 */
public interface PasswordService {
    String encode(String rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
