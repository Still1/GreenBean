package com.oc.greenbean.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtils {
    public static String encode(String password) {
        // XXX 这里的BCryptPasswordEncoder是否可以单例
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }
}
