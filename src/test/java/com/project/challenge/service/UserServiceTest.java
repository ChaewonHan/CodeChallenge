package com.project.challenge.service;

import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserDto;
import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.exception.LoginFailException;
import com.project.challenge.repository.UserRepository;
import com.project.challenge.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userNo(1L)
                .email("test@gmail.com")
                .username("test")
                .password("test!1234")
                .userStatus(UserStatus.ACTIVE)
                .build();
    }

    UserDto.loginUser createLoginUser() {
        return UserDto.loginUser.builder()
                .email("test@gmail.com")
                .password(passwordEncoder.encode("test!1234"))
                .build();

    }


    @Test
    @DisplayName("로그인 실패 - 아이디 또는 비밀번호가 일치하지 않으면 LoginFailException이 발생한다.")
    void loginFailExceptionTest() {
        //given
        UserDto.loginUser loginUser = createLoginUser();
        when(userRepository.findByEmail(eq(user.getEmail()), eq(UserStatus.ACTIVE))).thenReturn(of(user));

        //when
        assertThrows(LoginFailException.class, () -> userService.loginUser(loginUser));

        //then
        verify(userRepository, atLeastOnce()).findByEmail(loginUser.getEmail(), UserStatus.ACTIVE);

    }

}
