package com.project.challenge.service;

import com.project.challenge.domain.challenge.Challenge;
import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.repository.ChallengeRepository;
import com.project.challenge.repository.UserRepository;
import com.project.challenge.service.challenge.ChallengeService;
import com.project.challenge.service.file.AwsS3Service;
import com.project.challenge.service.file.FileService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChallengeServiceTest {

    @InjectMocks
    ChallengeService challengeService;

    @Mock
    ChallengeRepository challengeRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    FileService fileService;

    @Mock
    AwsS3Service awsS3Service;

    private String thumbnailPath = "https://holololo-bucket.s3.ap-northeast-2.amazonaws.com/challenge-profile/f08684e0-d819-4632-8f62-033db1d80781.png";

    User userEntity() {
        return User.builder()
                .userNo(1L)
                .email("test@gmail.com")
                .password("test!1234")
                .username("test")
                .userStatus(UserStatus.ACTIVE)
                .build();
    }

    ChallengeDto.addChallenge createChallenge() {
        return ChallengeDto.addChallenge
                .builder()
                .title("테스트")
                .content("테스트입니다.")
                .category("백엔드")
                .startDate(LocalDate.of(2022,01,01))
                .period(2)
                .build();
    }

    private MultipartFile createImageFile() {
        return new MockMultipartFile("testImg", "testImg.png", MediaType.IMAGE_PNG_VALUE, "testImg".getBytes());
    }


    @Test
    @DisplayName("이미지 없이 챌린지 생성 - 성공")
    void createChallengeSuccess() {
        //given
        ChallengeDto.addChallenge addChallenge = createChallenge();
        addChallenge.setWriter("test");
        User user = userEntity();
        given(userRepository.findUsername(user.getEmail())).willReturn("test");

        //when
        challengeService.saveChallenge(addChallenge, user.getEmail(), null);

        //then
        verify(userRepository, times(1)).findUsername(user.getEmail());
        verify(challengeRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("이미지가 첨부된 챌린지 생성 - 성공")
    void createChallengeWithImageSuccess() {
        //given
        ChallengeDto.addChallenge addChallenge = createChallenge();
        addChallenge.setWriter("test");
        User user = userEntity();
        MultipartFile file = createImageFile();

        given(userRepository.findUsername(user.getEmail())).willReturn("test");
        given(fileService.uploadThumbnail(file)).willReturn(thumbnailPath);

        //when
        challengeService.saveChallenge(addChallenge, user.getEmail(), file);

        //then
        verify(userRepository, times(1)).findUsername(user.getEmail());
        verify(fileService, times(1)).uploadThumbnail(file);
    }

}