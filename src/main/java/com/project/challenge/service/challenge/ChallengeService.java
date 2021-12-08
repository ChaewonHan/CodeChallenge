package com.project.challenge.service.challenge;

import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.repository.ChallengeRepository;
import com.project.challenge.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final FileService fileService;

    @Transactional
    public void saveChallenge(ChallengeDto.addChallenge challengeDto, @Nullable MultipartFile file) {
        if (!file.getOriginalFilename().isEmpty()) {
            String filePath = fileService.uploadThumbnail(file);
            challengeDto.setThumbnailFilePath(filePath);
        }
        challengeRepository.save(challengeDto.toEntity());
    }

}
