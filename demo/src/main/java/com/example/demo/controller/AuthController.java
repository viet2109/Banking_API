package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid UserDto.LogIn user) {
        return userService.logIn(user);
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody @Valid UserDto.SignUp user) {
        return userService.signUp(user);
    }

}
