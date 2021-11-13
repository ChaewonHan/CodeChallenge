package com.project.challenge.controller;

import com.project.challenge.config.session.SessionConst;
import com.project.challenge.domain.user.UserDto;
import com.project.challenge.exception.DuplicateEmailException;
import com.project.challenge.exception.LoginFailException;
import com.project.challenge.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;


    @Test
    @DisplayName("회원가입 성공")
    void createUserSuccess() throws Exception {
        UserDto.addUser addUser = UserDto.addUser.builder()
                .email("test@gmail.com")
                .username("test")
                .password("test!12345")
                .build();

        doNothing().when(userService).userSave(addUser);

        mockMvc.perform(post("/users/join")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("email", addUser.getEmail())
                .param("password", addUser.getPassword())
                .param("username", addUser.getUsername()))
                .andExpect(status().isFound())
                .andDo(print());

    }

    @Test
    @DisplayName("회원가입 실패 - 사용중인 이메일 입력")
    void createUserFail() throws Exception {
        UserDto.addUser addUser = UserDto.addUser.builder()
                .email("test@gmail.com")
                .username("test")
                .password("test!12345")
                .build();

        doThrow(new DuplicateEmailException("사용중인 이메일 입니다.")).when(userService).userSave(any());

        mockMvc.perform(post("/users/join")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("email", addUser.getEmail())
                .param("password", addUser.getPassword())
                .param("username", addUser.getUsername()))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() throws Exception {
        UserDto.loginUser loginUser = UserDto.loginUser
                .builder()
                .email("test@gmail.com")
                .password("test!12345")
                .build();

        doNothing().when(userService).loginUser(loginUser);

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("email", loginUser.getEmail())
                .param("password", loginUser.getPassword()))
                .andExpect(status().isFound())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 실패 - 이메일 혹은 비밀번호 불일치")
    void loginFail() throws Exception {
        UserDto.loginUser loginUser = UserDto.loginUser
                .builder()
                .email("test@gmail.com")
                .password("test!12345")
                .build();

        doThrow(new LoginFailException("이메일 또는 비밀번호가 잘못 입력 되었습니다.")).when(userService).loginUser(any());

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("email", loginUser.getEmail())
                .param("password", loginUser.getPassword()))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
