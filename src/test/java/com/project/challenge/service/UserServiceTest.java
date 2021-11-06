package com.project.challenge.service;

import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.dto.UserDto;
import com.project.challenge.repository.UserRepository;
import com.project.challenge.service.user.UserService;
import com.project.challenge.validator.UserValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserValidator userValidator;

    @InjectMocks
    UserService userService;

    @BeforeEach
    private UserDto.addUser addUser() {
        UserDto.addUser addUser = UserDto.addUser.builder()
                .userId("test")
                .password("test!")
                .username("테스트")
                .email("test@gmail.com")
                .build();
        return addUser;
    }


}
