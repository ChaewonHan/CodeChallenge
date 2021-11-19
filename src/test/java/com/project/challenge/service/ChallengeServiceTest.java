package com.project.challenge.service;

import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.repository.ChallengeRepository;
import com.project.challenge.service.challenge.ChallengeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChallengeServiceTest {

    @InjectMocks
    ChallengeService challengeService;

    @Mock
    ChallengeRepository challengeRepository;


    ChallengeDto.addChallenge createChallenge() {
        return ChallengeDto.addChallenge
                .builder()
                .title("테스트")
                .content("테스트입니다.")
                .category("백엔드")
                .startDate(new Date())
                .endDate(new Date())
                .build();
    }



}