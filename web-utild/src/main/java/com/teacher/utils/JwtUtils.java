package com.teacher.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JwtUtils {
    // 使用Keys类的secretKeyFor方法生成一个安全的密钥
    private static final Key signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // 定义JWT的有效期，单位为毫秒。这里设置为43200000毫秒，即12小时
    private static final Long expire = 43200000L;

    /**
     * 生成JWT令牌
     *
     * @param claims 包含在JWT中的声明信息，如用户ID、角色等
     * @return 生成的JWT字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        // 使用Jwts.builder()开始构建JWT
        String jwt = Jwts.builder()
                // 添加自定义声明
                .addClaims(claims)
                // 使用HS256签名算法和签名密钥对JWT进行签名
                .signWith(SignatureAlgorithm.HS256, signKey)
                // 设置JWT的过期时间为当前时间加上有效期
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                // 构建并返回JWT字符串
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     *
     * @param jwts 需要解析的JWT字符串
     * @return 解析后的Claims对象，包含JWT中的声明信息
     */
    public static Claims pareseJwt(String jwts) {
        // 使用Jwts.parser()开始解析JWT
        Claims claims = Jwts.parser()
                // 设置签名密钥，用于验证JWT的签名
                .setSigningKey(signKey)
                .build()
                // 解析并验证JWT的签名和声明
                .parseClaimsJws(jwts)
                // 获取JWT的主体部分，即Claims对象
                .getBody();
        return claims;
    }

//    public static void main(String[] args) {
//        // 创建一个包含自定义声明的Map
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userId", "123456");
//        claims.put("role", "teacher");
//
//        // 使用JwtUtils生成JWT
//        String jwt = JwtUtils.generateJwt(claims);
//        System.out.println(jwt);
//
//        Claims claims1 = JwtUtils.pareseJwt(jwt);
//        System.out.println(claims1);
//    }
}