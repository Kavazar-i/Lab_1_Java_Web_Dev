package com.kalosha.lab.lab_1_web_dev.util;

import lombok.extern.log4j.Log4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Log4j
public class HashUtil {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes());

            // bytes to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("Failed to hash password", e);
            throw new RuntimeException(e);
        }
    }
}
