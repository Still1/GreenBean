package com.oc.greenbean.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加密工具类
 */
public class EncryptionUtils {
    /**
     * 通过BCrypt算法加密
     *
     * @param password 密码原文
     * @return 密码密文
     */
    public static String encode(String password) {
        // XXX 这里的BCryptPasswordEncoder是否可以单例
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
