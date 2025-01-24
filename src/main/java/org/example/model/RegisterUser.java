package org.example.model;

import lombok.Data;

@Data
public class RegisterUser {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}
