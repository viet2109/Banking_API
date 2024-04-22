package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.Error;
import com.example.demo.util.UserAuthDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

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

        return convertEntityToDto(newUser, null);
    }

    public UserDto logIn(UserDto.LogIn user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getPhone(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return getCurrentUserDto(user.getPhone(), jwtService.generateToken(user.getPhone()));
        }
        return null;
    }

    private UserDto convertEntityToDto(User newUser, String token) {
        return UserDto
                .builder()
                .name(newUser.getName())
                .phone(newUser.getPhone())
                .token(token)
                .email(newUser.getEmail())
                .address(newUser.getAddress())
                .build();
    }

    public UserDto getCurrentUserDto(String phone, String token) {
        User userEntity = userRepository.findByPhone(phone).orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));
        return convertEntityToDto(userEntity, token);
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return userRepository.findByPhone(phone).map(UserAuthDetails::new).orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));
    }
}
