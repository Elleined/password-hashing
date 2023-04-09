package com.passwordHashing;

import java.security.SecureRandom;

public abstract class SaltGeneratorUtil {

    private final static SecureRandom random = new SecureRandom();

    public static byte[] getSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
