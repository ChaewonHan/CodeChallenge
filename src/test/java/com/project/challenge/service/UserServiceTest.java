package com.project.challenge.service;

import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserDto;
import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.repository.UserRepository;
import com.project.challenge.service.user.UserService;
import com.project.challenge.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

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
