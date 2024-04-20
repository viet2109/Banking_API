package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.Error;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDto signUp(UserDto.SignUp user) {

        //check email and phone
        userRepository.findByPhone(user.getPhone()).ifPresent(entity -> {
            throw new AppException(Error.DUPLICATED_USER);
        });

        User newUser = User.builder().name(user.getName()).phone(user.getPhone()).password(passwordEncoder.encode(user.getPassword())).build();

        try {
            userRepository.save(newUser);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

        return convertEntityToDto(newUser);
    }

    private UserDto convertEntityToDto(User newUser) {
        return UserDto.builder().name(newUser.getName()).phone(newUser.getPhone()).build();
    }

}
