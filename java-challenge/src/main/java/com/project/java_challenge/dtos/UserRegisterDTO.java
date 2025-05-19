package com.project.java_challenge.dtos;

import com.project.java_challenge.validations.ExistsByUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserRegisterDTO {

    @Setter
    @ExistsByUsername
    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 4, max = 18, message = "The username must be between 4 and 18 characters long.")
    private String username;

    @Setter
    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, message = "The password must contain at least 8 characters.")
    private String password;

    private boolean admin;

    public UserRegisterDTO(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public UserRegisterDTO() {

    }
}