package com.tims.common.security.service;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

/**
 * 加密服务
 */
public interface EncryptionService {

    /**
     * 加密文本
     * @param plaintext 明文
     * @return 密文
     */
    String encrypt(String plaintext);

    /**
     * 解密文本
     * @param encryptedText 密文
     * @return 明文
     */
    String decrypt(String encryptedText);
}
