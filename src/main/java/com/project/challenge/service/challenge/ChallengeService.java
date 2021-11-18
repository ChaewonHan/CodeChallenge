package com.project.challenge.service.challenge;

import com.project.challenge.domain.challenge.Challenge;
import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.repository.ChallengeRepository;
import com.project.challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    public void saveChallenge(ChallengeDto.addChallenge challengeDto) {
        Challenge challenge = challengeDto.toEntity();
        challengeRepository.save(challenge);
    }

}
