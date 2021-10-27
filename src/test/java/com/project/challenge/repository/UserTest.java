package com.project.challenge.repository;

import com.project.challenge.entity.user.User;
import com.project.challenge.entity.user.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Test
    void BaseEntityTest() {
        //given
        String name = "test";
        String password = "test!";
        String email = "coding@gmail.com";

        User user = new User(name, password, email, UserStatus.USER);
        userRepository.save(user);

        //when
        User findUser = userRepository.findAll().get(0);

        //then
        System.out.println("date = " + findUser.getCreatedDate());
    }
}