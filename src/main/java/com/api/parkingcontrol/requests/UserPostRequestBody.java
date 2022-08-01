package com.api.parkingcontrol.requests;

import com.api.parkingcontrol.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UserPostRequestBody {

    @NotBlank(message = "The field `username` cannot be empty or null")
    @Length(min = 3, message = "The field `username` cannot be less than 3 characters")
    private String username;

    @NotBlank(message = "The field `username` cannot be empty or null")
    @Length(min = 6, message = "The field `password` cannot be less than 6 characters")
    private String password;

    public User newUser() {
        User newUser = new User();
        newUser.setUsername(this.username);
        newUser.setPassword(this.password);
        return newUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}