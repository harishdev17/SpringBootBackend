package com.hacktober.blog.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {

    public static BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Encode password to Base64
    public static String encode(String password) {
        return bCryptPasswordEncoder().encode(password);
    }

    // Decode password (if needed for verification)
    public static boolean match(String password, String encodedPassword) {
        return bCryptPasswordEncoder().matches(password, encodedPassword);
    }
}
