package com.project.challenge.service.challenge;

import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.repository.ChallengeRepository;
import com.project.challenge.repository.UserRepository;
import com.project.challenge.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private static final String DEFAULT_FILE_PATH = "https://holololo-bucket.s3.ap-northeast-2.amazonaws.com/challenge-profile/default/default.png";
    private static final String DEFAULT_FILE_ORIGINAL_NAME = "default.png";

    @Transactional
    public void saveChallenge(ChallengeDto.addChallenge challengeDto, String email, MultipartFile file) {
        if (!file.isEmpty()) {
            String filePath = fileService.uploadThumbnail(file);
            challengeDto.setImage(filePath, file.getOriginalFilename());
        } else {
            challengeDto.setImage(DEFAULT_FILE_PATH, DEFAULT_FILE_ORIGINAL_NAME);
        }

        challengeDto.setWriter(getLoginUsername(email));
        challengeRepository.save(challengeDto.toEntity());
    }

    @Transactional
    public String getLoginUsername(String email) {
        return userRepository.findUsername(email);
    }

}
