package com.patientmanagementsystem.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class LoginRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "This should be valid email")
    private String email;

    @NotBlank(message = "pass is required")
    @Size(min = 2, message = "pass must be at east 2 chars")
    private String password;
}
