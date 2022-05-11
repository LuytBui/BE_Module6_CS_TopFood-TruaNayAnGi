package com.codegym.model.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterForm {
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    public boolean confirmPasswordMatch(){
        return password.equals(confirmPassword);
    }
}
