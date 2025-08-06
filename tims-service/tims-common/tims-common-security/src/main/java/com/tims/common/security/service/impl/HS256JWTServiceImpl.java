package com.tims.common.security.service.impl;

import com.tims.common.security.properties.JwtProperties;
import com.tims.common.security.service.JWTService;
import com.tims.common.security.utill.SecretUtil;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class HS256JWTServiceImpl implements JWTService {
    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;

    /**
     * 构造函数，用于初始化HS256JWTServiceImpl实例。
     *
     * <p>该构造函数接收一个JwtProperties对象，通过它获取配置的JWT密钥，并将其转换为用于JWT签名和验证的SecretKey对象。
     * 具体步骤如下：
     * <ol>
     * <li>将传入的JwtProperties对象赋值给类的成员变量jwtProperties，以便后续方法使用其中的配置信息。</li>
     * <li>从jwtProperties中获取JWT密钥字符串secret。</li>
     * <li>调用SecretUtil类的静态方法getHSSecretKeyFromBase64，将Base64编码的密钥字符串转换为HS256算法所需的SecretKey对象，并赋值给类的成员变量secretKey。</li>
     * </ol>
     *
     * @param jwtProperties 包含JWT相关配置属性的对象，如过期时间、密钥等配置信息。
     * @see JwtProperties
     * @see SecretUtil
     */
    public HS256JWTServiceImpl(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;

        // 创建SecretKey
        String secret = jwtProperties.getSecret();
        secretKey = SecretUtil.getHSSecretKeyFromBase64(secret);
    }

    /**
     * 重写方法，用于创建JWT令牌。
     *
     * <p>此方法根据传入的声明（claims）以及配置的过期时间等信息生成一个JWT令牌。
     * 具体步骤如下：
     * <ol>
     * <li>从配置属性中获取JWT的过期时间（以毫秒为单位），并根据当前时间计算出过期日期。</li>
     * <li>使用Jwts.builder构建JWT，设置签名密钥、声明信息以及过期日期。</li>
     * <li>调用compact方法生成紧凑的JWT字符串并返回。</li>
     * </ol>
     *
     * @param claims 包含JWT声明的Map集合，例如用户信息等。声明中的键值对将包含在生成的JWT中。
     * @return 生成的JWT令牌字符串。如果在生成过程中出现任何异常，将抛出相应的异常（取决于Jwts.builder的实现）。
     * @see Jwts
     * @see JwtProperties
     */
    @Override
    public String createJWTToken(Map<String, Object> claims) {
        // 设置过期时间
        long expiration = jwtProperties.getExpiration();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);


        // 创建JWT
        return Jwts.builder()
                .signWith(secretKey, Jwts.SIG.HS256)
                .claims(claims)
                .expiration(expiryDate)
                .compact();
    }

    /**
     * 重写方法，用于解析JWT令牌并获取其负载中的声明信息。
     *
     * <p>此方法接收一个JWT令牌字符串，通过使用预定义的密钥对其进行验证和解析，
     * 最终返回包含在JWT负载中的声明信息，以键值对（Map）的形式呈现。
     *
     * <p>具体操作步骤如下：
     * <ol>
     * <li>使用Jwts.parser()创建一个JWT解析器。</li>
     * <li>调用verifyWith(secretKey)方法，使用预定义的密钥对JWT进行验证，
     * 确保JWT在传输过程中没有被篡改。</li>
     * <li>调用build()方法构建解析器实例。</li>
     * <li>调用parseSignedClaims(jwtToken)方法解析传入的JWT令牌字符串，
     * 并获取已签名的声明。</li>
     * <li>最后，调用getPayload()方法获取JWT负载，即包含各种声明信息的Map对象。</li>
     * </ol>
     *
     * @param jwtToken 待解析的JWT令牌字符串。
     * @return 一个Map对象，其中包含JWT负载中的声明信息，键为声明的名称，值为声明的值。
     * @throws JwtException 如果JWT令牌无效、签名验证失败、过期或其他与JWT解析相关的错误，将抛出此异常。
     * @see Jwts
     */
    @Override
    public Map<String, Object> parseJWTToken(String jwtToken) throws JwtException{
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }
}
