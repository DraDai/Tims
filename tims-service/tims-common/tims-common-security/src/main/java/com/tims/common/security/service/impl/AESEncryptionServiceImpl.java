package com.tims.common.security.service.impl;

import com.tims.common.security.properties.AesProperties;
import com.tims.common.security.service.EncryptionService;
import com.tims.common.security.utill.SecretUtil;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AESEncryptionServiceImpl implements EncryptionService {
    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
    private final SecretKey secretKey;

    public AESEncryptionServiceImpl(AesProperties aesProperties) {
        secretKey = SecretUtil.getSecretKeyFromBase64ForAES(aesProperties.getSecret());
    }

    /**
     * 重写加密方法，使用AES算法对传入的明文进行加密。
     *
     * <p>该方法的加密流程如下：
     * <ol>
     *     <li>获取AES加密算法的{@link Cipher}实例，使用的算法名称由{@code AES_ALGORITHM}常量指定。</li>
     *     <li>生成一个16字节的随机初始化向量（IV），AES算法通常使用16字节的IV。</li>
     *     <li>基于生成的IV创建一个{@link IvParameterSpec}对象，用于指定加密模式中的IV。</li>
     *     <li>以加密模式初始化{@link Cipher}实例，使用类中的{@code secretKey}和前面创建的IV参数。</li>
     *     <li>将传入的明文以UTF - 8编码转换为字节数组，并调用{@link Cipher#doFinal(byte[])}方法进行加密，得到加密后的字节数组。</li>
     *     <li>创建一个新的字节数组，长度为IV长度与加密后字节数组长度之和，将IV和加密后的字节数组合并到这个新数组中。</li>
     *     <li>将合并后的字节数组进行Base64编码，并返回编码后的字符串作为加密结果。</li>
     * </ol>
     *
     * <p>如果在加密过程中发生任何异常，例如算法不存在、填充模式不支持、密钥无效等，
     * 会捕获这些异常并抛出一个{@link RuntimeException}，包装原始异常以简化调用者的异常处理。
     *
     * @param plaintext 待加密的明文字符串。
     * @return 加密后的Base64编码字符串。
     * @throws RuntimeException 如果在加密过程中发生{@link NoSuchAlgorithmException}、
     *                         {@link NoSuchPaddingException}、
     *                         {@link InvalidAlgorithmParameterException}、
     *                         {@link IllegalBlockSizeException}、
     *                         {@link BadPaddingException}或{@link InvalidKeyException}异常。
     * @see Cipher
     * @see SecureRandom
     * @see IvParameterSpec
     * @see Base64
     */
    @Override
    public String encrypt(String plaintext){
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            byte[] iv = new byte[16]; // AES 默认 16 字节 IV
            new SecureRandom().nextBytes(iv); // 生成随机 IV
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            byte[] combined = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decrypt(String encryptedText) {
        byte[] combined = Base64.getDecoder().decode(encryptedText);
        byte[] iv = new byte[16];
        byte[] cipherBytes = new byte[combined.length - 16];

        System.arraycopy(combined, 0, iv, 0, 16);
        System.arraycopy(combined, 16, cipherBytes, 0, cipherBytes.length);


        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] decryptedBytes = cipher.doFinal(cipherBytes);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 BadPaddingException | NoSuchPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
