package com.tims.common.security.service;

import java.util.Map;

/**
 * JWT服务
 */
public interface JWTService {

    /**
     * 创建JWT令牌
     * @param claims 令牌负载
     * @return JWT令牌
     */
    String createJWTToken(Map<String, Object>  claims);

    /**
     * 解析JWT令牌
     * @param jwtToken JWT令牌
     * @return 令牌负载
     */
    Map<String, Object> parseJWTToken(String jwtToken);
}
