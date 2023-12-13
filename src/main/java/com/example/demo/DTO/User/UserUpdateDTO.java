package com.example.demo.DTO.User;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    private String userName;

    @Email
    private String email;
    private String password;

    public UserUpdateDTO(String name, String mail, String test){
        this.userName = name;
        this.email = mail;
        this.password = test;
    }
}
