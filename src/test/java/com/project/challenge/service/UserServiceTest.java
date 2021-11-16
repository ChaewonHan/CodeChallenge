package com.project.challenge.service;

import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserDto;
import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.exception.user.DuplicateEmailException;
import com.project.challenge.exception.user.DuplicateUsernameException;
import com.project.challenge.exception.user.LoginFailException;
import com.project.challenge.repository.UserRepository;
import com.project.challenge.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(userService, "passwordEncoder", passwordEncoder);
    }

    User userEntity() {
        return User.builder()
                .userNo(1L)
                .email("test@gmail.com")
                .password(passwordEncoder.encode("test!1234"))
                .username("test")
                .userStatus(UserStatus.ACTIVE)
                .build();
    }


    UserDto.addUser createJoinUser() {
        return UserDto.addUser.builder()
                .email("test2@gmail.com")
                .username("test2")
                .password("test!1234")
                .build();
    }


    UserDto.loginUser createLoginUser() {
        return UserDto.loginUser.builder()
                .email("test@gmail.com")
                .password("test!1234")
                .build();

    }

    @Test
    @DisplayName("회원가입 성공")
    void userSaveSuccess() {
        //given
        UserDto.addUser addUser = createJoinUser();

        //when
        userService.userSave(addUser);

        //then
        verify(userRepository, times(1)).existsByEmailAndUserStatus(addUser.getEmail(), UserStatus.ACTIVE);
        verify(userRepository, times(1)).existsByUsernameAndUserStatus(addUser.getUsername(), UserStatus.ACTIVE);

    }

    @Test
    @DisplayName("회원가입 실패 - 중복된 이메일이면 DuplicateEmailException이 발생한다.")
    void duplicateEmail() {
        //given
        UserDto.addUser addUser = createJoinUser();
        given(userRepository.existsByEmailAndUserStatus(addUser.getEmail(), UserStatus.ACTIVE)).willReturn(Boolean.TRUE);

        //when
        assertThrows(DuplicateEmailException.class, () -> userService.userSave(addUser));

        //then
        verify(userRepository, times(1)).existsByEmailAndUserStatus(addUser.getEmail(), UserStatus.ACTIVE);

    }

    @Test
    @DisplayName("회원가입 실패 - 중복된 닉네임이면 DuplicateUsernameException이 발생한다.")
    void duplicateUsername() {
        //given
        UserDto.addUser addUser = createJoinUser();
        given(userRepository.existsByUsernameAndUserStatus(addUser.getUsername(), UserStatus.ACTIVE)).willReturn(Boolean.TRUE);

        //when
        assertThrows(DuplicateUsernameException.class, () -> userService.userSave(addUser));

        //then
        verify(userRepository, times(1)).existsByUsernameAndUserStatus(addUser.getUsername(), UserStatus.ACTIVE);

    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() {
        //given
        UserDto.loginUser loginUser = createLoginUser();
        User user = userEntity();
        given(passwordEncoder.matches(any(), any())).willReturn(true);
        given(userRepository.findByEmailAndUserStatus(loginUser.getEmail(), UserStatus.ACTIVE)).willReturn(Optional.ofNullable(user));

        //when
        userService.loginUser(loginUser);

        //then
        verify(userRepository, times(1)).findByEmailAndUserStatus(loginUser.getEmail(), UserStatus.ACTIVE);
    }

    @Test
    @DisplayName("로그인 실패 - 이메일 또는 비밀번호가 일치하지 않으면 LoginFailException이 발생한다.")
    void loginFail() {
        //given
        UserDto.loginUser loginUser = createLoginUser();
        User user = userEntity();
        given(passwordEncoder.matches(any(), any())).willReturn(false);
        given(userRepository.findByEmailAndUserStatus(loginUser.getEmail(), UserStatus.ACTIVE)).willReturn(Optional.ofNullable(user));

        //when
        assertThrows(LoginFailException.class, () -> userService.loginUser(loginUser));

        //then
        verify(userRepository, times(1)).findByEmailAndUserStatus(loginUser.getEmail(), UserStatus.ACTIVE);
    }



}
