package com.hacktober.blog.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Utils {

    // Encode password to Base64
    public static String encode(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }

    // Decode password (if needed for verification)
    public static String decode(String encodedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
