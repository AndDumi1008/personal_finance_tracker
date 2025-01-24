package org.example.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
