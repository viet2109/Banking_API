package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String address;
    private String email;
    private String phone;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SignUp {

        @NotEmpty(message = "The phone is mandatory")
        @Pattern(regexp = "^(?:\\+84)?\\d{10,15}$", message = "The phone has invalid")
        private String phone;

        @NotEmpty(message = "The password is mandatory")
        @Pattern(regexp = "^(?!.*\\s).{6,}$\n", message = "The password length must be greater than or equal 6 and has no any space character")
        private String password;

        @NotEmpty(message = "The name is mandatory")
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LogIn {
        @NotEmpty(message = "The phone is mandatory")
        @Pattern(regexp = "^(?:\\+84)?\\d{10,15}$", message = "The phone number has invalid")
        private String phone;

        @NotEmpty(message = "The password is mandatory")
        @Pattern(regexp = "^(?!.*\\s).{6,}$\n", message = "The password length must be greater than or equal 6 and has no any space character")
        private String password;


    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Update {
        private int id;

        @Pattern(regexp = "^(?:\\+84)?\\d{10,15}$", message = "The phone number has invalid")
        private String phone;

        @Pattern(regexp = "^(?!.*\\s).{6,}$\n", message = "The password length must be greater than or equal 6 and has no any space character")
        private String password;

        private String name;

        @Email(message = "The phone number has invalid")
        private String email;

        private String address;
    }

}
