package com.project.challenge.repository;

import com.project.challenge.domain.challenge.Challenge;
import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.domain.challenge.ChallengeStatus;
import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ChallengeRepositoryTest {

    @Autowired
    ChallengeRepository challengeRepository;

    User userEntity() {
        return User.builder()
                .userNo(1L)
                .email("test@gmail.com")
                .password("test!1234")
                .username("test")
                .userStatus(UserStatus.ACTIVE)
                .build();
    }

    @Test
    @DisplayName("challenge 저장")
    void save() {
        //given
        ChallengeDto.addChallenge addChallenge = ChallengeDto.addChallenge
                .builder()
                .title("테스트")
                .content("테스트입니다.")
                .category("백엔드")
                .startDate(LocalDate.of(2022,01,01))
                .period(2)
                .build();


        Challenge challenge = addChallenge.toEntity();

        //when
        Challenge dbChallenge = challengeRepository.save(challenge);

        //then
        Assertions.assertThat(dbChallenge.getEndDate()).isEqualTo(challenge.getEndDate());
    }
}
