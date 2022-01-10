package com.project.challenge.controller;

import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.service.challenge.ChallengeService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ChallengeController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class ChallengeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ChallengeService challengeService;

    MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        session.setAttribute("loginUser", "test123");
    }

    @AfterEach
    void clean() {
        session.clearAttributes();
    }

    @Test
    @DisplayName("챌린지 생성 성공")
    void createChallengeSuccess() throws Exception {
        ChallengeDto.addChallenge challenge = ChallengeDto.addChallenge
                .builder()
                .title("테스트")
                .content("테스트입니다.")
                .category("백엔드")
                .startDate(LocalDate.of(2022,01,01))
                .period(2)
                .build();

        //doNothing().when(challengeService).saveChallenge(challenge);

        mockMvc.perform(post("/challenges")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .session(session)
                .param("title", challenge.getTitle())
                .param("content", challenge.getContent())
                .param("category", challenge.getCategory())
                .param("startDate", String.valueOf(challenge.getStartDate()))
                .param("period", String.valueOf(challenge.getPeriod())))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
