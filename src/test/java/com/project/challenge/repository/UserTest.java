package com.project.challenge.repository;

import com.project.challenge.domain.user.User;
import com.project.challenge.dto.UserDto;
import com.project.challenge.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    void BaseEntityTest() {
        //given
        String id = "test";
        String name = "테스트";
        String password = "test!";
        String email = "coding@gmail.com";

        User user = User.builder()
                .userId(id)
                .password(password)
                .username(name)
                .email(email)
                .build();

        userRepository.save(user);

        //when
        User findUser = userRepository.findAll().get(0);

        //then
        System.out.println("date = " + findUser.getCreatedDate());
    }

    @Test
    void save() {
        //given
        UserDto userDto = new UserDto("test", "test!", "coding@gmail.com", "테스트");

        //when
        String saveId = userService.userSave(userDto);

        //then
    }


}