package com.project.challenge.controller;

import com.project.challenge.domain.user.UserDto;
import com.project.challenge.validator.addUserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private addUserFormValidator addUserFormValidator;

    UserDto.addUser addUser() {
        UserDto.addUser addUser = UserDto.addUser.builder()
                .email("test@gmail.com")
                .password("test!")
                .username("테스트")
                .build();
        return addUser;
    }

}
