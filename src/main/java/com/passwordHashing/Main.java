package com.passwordHashing;

public class Main {
    public static void main(String[] args) {
        CredentialRepository credentialRepository = new CredentialRepository();
        // d7c6fea168fb5632ffb8fb7acebd076d2729c2812787830d662e0b48fd91b226
        // [13, 39, 52, -90, 83, -14, -107, 111, -110, -49, 8, -5, 49, 122, -78, -75]

        String username = "demadegu2@gmail.com";
        byte[] salt = credentialRepository.getSalt(username);
        String userInputHashedPassword = credentialRepository.doHashing("Denielle", salt);
        System.out.println("User Input Hashed Password " + userInputHashedPassword);

        String realPassword = credentialRepository.getHashedPassword(username);
        System.out.println("Real password " + realPassword);

        if (realPassword.equals(userInputHashedPassword)) {
            System.out.println("Correct Password");
        } else {
            System.out.println("Wrong Password");
        }
        System.out.println("Executed All");
    }
}
