package com.project.challenge.repository;

import com.project.challenge.domain.user.User;
import com.project.challenge.dto.UserDto;
import com.project.challenge.exception.DuplicateException;
import com.project.challenge.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    //테스트용 데이터
    final String userId = "test";
    final String password = "test!";
    final String email = "coding@gmail.com";
    final String username = "테스트";
    final UserDto userDto = new UserDto(userId, password, email, username);

    User testUserEntity(UserDto userDto) {
        return User.builder()
                .userNo(1L)
                .userId(userDto.getUserId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .deleteAccount(true)
                .build();
    }


    @Test
    @DisplayName("회원가입 - 성공")
    void createUser() {
        //given
        User user = testUserEntity(userDto);
        given(userRepository.save(any(User.class))).willReturn(user);

        //when
        String saveId = userService.userSave(userDto);

        //then
        then(userRepository).should(times(1)).existsByUserIdAndDeleteAccount(userId, false);
        then(userRepository).should(times(1)).existsByUsernameAndDeleteAccount(username, false);
        then(userRepository).should(times(1)).existsByEmailAndDeleteAccount(email, false);

    }


}
