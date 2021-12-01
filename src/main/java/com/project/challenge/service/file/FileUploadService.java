package com.project.challenge.service.file;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileUpload fileUpload;

    public String uploadImg(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        String filename = createFileName(file.getOriginalFilename());

        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            fileUpload.uploadFile(inputStream, objectMetadata, filename);
        } catch (IOException e) {
           throw new IllegalArgumentException(String.format("파일 변환 중 오류가 발생했습니다: (%s)", file.getOriginalFilename()));
        }

        return fileUpload.getFileUrl(filename);
    }

    //기존 확장자는 유지하고 파일 이름이 겹치지 않게 랜덤한 파일이름을 생성한다.
    public String createFileName(String filename) {
        return UUID.randomUUID().toString() + "." + getFileExtension(filename);
    }

    //파일 확장자 추출
    public String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
