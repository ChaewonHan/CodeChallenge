package com.project.challenge.repository;

import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    //테스트용 데이터
    final String userId = "test";
    final String password = "test!";
    final String email = "coding@gmail.com";
    final String username = "테스트";
    final UserDto userDto = new UserDto(userId, password, email, username);

}
