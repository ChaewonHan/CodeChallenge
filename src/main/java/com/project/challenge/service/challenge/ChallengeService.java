package com.project.challenge.service.challenge;

import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.repository.ChallengeRepository;
import com.project.challenge.repository.UserRepository;
import com.project.challenge.service.file.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final FileUploadService fileUploadService;

    public void saveChallenge(ChallengeDto.addChallenge challengeDto, @Nullable MultipartFile multipartFile) {
        if (multipartFile != null) {
            String filePath = fileUploadService.uploadImg(multipartFile);
            challengeDto.setFilePath(filePath);
        }
        challengeRepository.save(challengeDto.toEntity());
    }

}
