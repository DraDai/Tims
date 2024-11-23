package com.teacher.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Util {
    /**
     * 对字符串进行MD5加密
     *
     * @param input 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String toMD5(String input) {
        try {
            // 获取MD5 MessageDigest 实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算MD5哈希值
            md.update(input.getBytes());
            byte[] digest = md.digest();
            // 将字节转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
