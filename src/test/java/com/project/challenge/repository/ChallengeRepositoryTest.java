package com.project.challenge.repository;

import com.project.challenge.domain.challenge.Challenge;
import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.domain.challenge.ChallengeStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ChallengeRepositoryTest {

    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    @DisplayName("challenge 저장")
    void save() {
        //given
        ChallengeDto.addChallenge addChallenge = ChallengeDto.addChallenge
                .builder()
                .title("테스트")
                .content("테스트입니다.")
                .category("백엔드")
                .startDate(new Date())
                .endDate(new Date())
                .build();

        Challenge challenge = addChallenge.toEntity();

        //when
        Challenge dbChallenge = challengeRepository.save(challenge);

        //then
        Assertions.assertThat(challenge.getChallengeTitle()).isEqualTo(challenge.getChallengeTitle());
    }
}
