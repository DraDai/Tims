package com.tims.common.security.utill;

import com.tims.common.security.enums.AESKeyType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 密钥工具类
 */
public class SecretUtil {
    /**
     * 创建Base64编码的HS256密钥
     * @return 密钥
     */
    public static String createBase64HS256Secret(){
        SecretKey secretKey = Jwts.SIG.HS256.key().build();
        return Encoders.BASE64.encode(secretKey.getEncoded());
    }

    /**
     * 获取HMAC-SHA 系列算法的 SecretKey
     * @param secretKey Base64编码的密钥
     * @return 密钥
     */
    public static SecretKey getHSSecretKeyFromBase64(String secretKey) {
        byte[] decode = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(decode);
    }

    /**
     * 静态方法，用于生成基于AES（高级加密标准）密钥的Base64编码字符串。
     *
     * <p>此方法根据指定的AES密钥类型生成一个随机的AES密钥，并将其以Base64编码的形式返回。具体步骤如下：
     * <ol>
     * <li>创建一个安全的随机数生成器{@link SecureRandom}实例。</li>
     * <li>根据传入的{@link AESKeyType}获取所需的密钥字节大小，并创建一个相应大小的字节数组{@code keyBytes}。</li>
     * <li>使用随机数生成器填充{@code keyBytes}数组，生成随机的AES密钥字节。</li>
     * <li>基于生成的字节数组创建一个{@link SecretKeySpec}对象，指定加密算法为"AES"。</li>
     * <li>提取{@link SecretKeySpec}对象的编码字节，并使用Base64编码器将其编码为字符串后返回。</li>
     * </ol>
     *
     * @param aesKeyType AES密钥类型，决定生成的密钥字节大小。
     * @return 生成的AES密钥的Base64编码字符串。
     * @see SecureRandom
     * @see AESKeyType
     * @see SecretKeySpec
     * @see Encoders
     */
    public static String createBase64ForAESSecret(AESKeyType aesKeyType){
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[aesKeyType.getKeySizeBytes()];
        random.nextBytes(keyBytes);
        SecretKeySpec aes = new SecretKeySpec(keyBytes, "AES");
        return Encoders.BASE64.encode(aes.getEncoded());
    }

    /**
     * 静态方法，用于从Base64编码的字符串中获取AES（高级加密标准）SecretKey。
     *
     * <p>此方法接收一个Base64编码的字符串，该字符串代表AES加密算法使用的密钥。
     * 方法将Base64编码的字符串解码为字节数组，然后使用这些字节创建一个SecretKeySpec对象，
     * 该对象实现了SecretKey接口，可用于AES加密和解密操作。
     *
     * <p>具体步骤如下：
     * <ol>
     * <li>使用Decoders.BASE64的decode方法将传入的Base64编码的密钥字符串解码为字节数组{@code decodeKey}。</li>
     * <li>使用解码后的字节数组{@code decodeKey}创建一个新的SecretKeySpec对象。构造函数的参数含义为：
     *     <ul>
     *         <li>第一个参数为字节数组，即解码后的密钥字节。</li>
     *         <li>第二个参数为偏移量，这里设置为0，表示从字节数组的起始位置开始使用。</li>
     *         <li>第三个参数为要使用的字节数，这里使用整个解码后的字节数组长度。</li>
     *         <li>第四个参数指定加密算法为"AES"。</li>
     *     </ul>
     * </li>
     * <li>返回创建好的SecretKeySpec对象，它实现了SecretKey接口，可用于AES相关的加密和解密操作。</li>
     * </ol>
     *
     * @param secretKey Base64编码的AES密钥字符串。
     * @return 一个实现了SecretKey接口的SecretKeySpec对象，用于AES加密和解密操作。
     * @see Decoders
     * @see SecretKeySpec
     */
    public static SecretKey getSecretKeyFromBase64ForAES(String secretKey) {
        byte[] decodeKey = Decoders.BASE64.decode(secretKey);
        return new SecretKeySpec(decodeKey, 0, decodeKey.length, "AES");
    }
}
