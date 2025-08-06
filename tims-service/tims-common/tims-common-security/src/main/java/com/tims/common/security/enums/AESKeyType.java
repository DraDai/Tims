package com.tims.common.security.enums;

/**
 * AES密钥长度枚举
 */
public enum AESKeyType {
    AES_128(16),
    AES_192(24),
    AES_256(32);

    private final int keySizeBytes;
    AESKeyType(int KeySizeBytes) {
        keySizeBytes = KeySizeBytes;
    }

    public int getKeySizeBytes() {
        return keySizeBytes;
    }

    public int getKeySizeBits() {
        return keySizeBytes * 8;
    }
}
