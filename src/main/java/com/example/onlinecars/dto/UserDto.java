package com.example.onlinecars.dto;

import com.example.onlinecars.validation.PhoneNumberValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    @NotBlank(message = "Shouldn't be empty space")
    private String fullName;
    @PhoneNumberValidation(message = "Wrong phone number")
    @NotBlank(message = "Shouldn't be empty space")
    private String phoneNumber;
    @NotBlank(message = "Shouldn't be empty space")
    private String password;
}
