package com.passwordHashing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credential {

    private String email;
    private String password;
    private byte[] salt;
}
