package com.teacher.utils;

import org.mindrot.jbcrypt.BCrypt;

public final class JbcryptUtil {
    /**
     * 使用bcrypt生成密码的哈希值
     *
     * @param plainTextPassword 明文密码
     * @return 哈希后的密码
     */
    public static String hashPassword(String plainTextPassword) {
        // 自动生成盐并哈希密码，$2a$是bcrypt的标识，后面跟的数字是工作因子
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * 验证明文密码是否与哈希密码匹配
     *
     * @param plainTextPassword 明文密码
     * @param hashedPassword    哈希后的密码
     * @return 验证结果
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        // 验证密码是否匹配
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
