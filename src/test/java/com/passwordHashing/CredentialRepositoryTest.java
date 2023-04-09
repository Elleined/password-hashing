package com.passwordHashing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class CredentialRepositoryTest {
    private final CredentialRepository credentialRepository = new CredentialRepository();

    @Test
    void insertCredential() {
        byte[] salt = SaltGeneratorUtil.getSalt();
        Credential credential = new Credential("demadegu2@gmail.com", "Denielle", salt);
        credentialRepository.insertCredential(credential);
    }

    @Test
    void getSalt() {
        String username = "demadegu2@gmail.com";
        byte[] retrievedSalt = credentialRepository.getSalt(username);
        System.out.println("Retrieved salt " + Arrays.toString(retrievedSalt));
    }

    @Test
    void getPassword() {
        String username = "demadegu2@gmail.com";
        String retrievedPassword = credentialRepository.getHashedPassword(username);
        System.out.println("Retrieved hashed password " + retrievedPassword);
    }

    @Test
    void doHashing() {
        byte[] salt = credentialRepository.getSalt("demadegu2@gmail.com");
        String hashedPassword = credentialRepository.doHashing("Denielle", salt);
        System.out.println("Hashed Password " + hashedPassword);
    }
}