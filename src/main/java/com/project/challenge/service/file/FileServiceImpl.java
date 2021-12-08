package com.project.challenge.service.file;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final AwsS3Service awsS3Service;

    @Override
    public String uploadImg(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        String filename = createFileName(file.getOriginalFilename());

        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            awsS3Service.uploadFile(inputStream, objectMetadata, filename);
        } catch (IOException e) {
           throw new IllegalArgumentException(String.format("파일 변환 중 오류가 발생했습니다: (%s)", file.getOriginalFilename()));
        }

        return awsS3Service.getFileUrl(filename);
    }

    @Override
    public String uploadThumbnail(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        String filename = "challenge-profile/" + createFileName(file.getOriginalFilename());

        try {
            BufferedImage originalImg = ImageIO.read(file.getInputStream());
            BufferedImage thumbnailImg = Thumbnails.of(originalImg).size(400, 400).asBufferedImage();

            ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
            ImageIO.write(thumbnailImg, getFileExtension(filename), thumbOutput);
            byte[] thumbByte = thumbOutput.toByteArray();

            objectMetadata.setContentLength(thumbByte.length);
            objectMetadata.setContentType(file.getContentType());

            InputStream inputStream = new ByteArrayInputStream(thumbByte);
            awsS3Service.uploadFile(inputStream, objectMetadata, filename);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 오류가 발생했습니다: (%s)", file.getOriginalFilename()));
        }

        return awsS3Service.getFileUrl(filename);
    }

    //기존 확장자는 유지하고 파일 이름이 겹치지 않게 랜덤한 파일이름을 생성한다.
    @Override
    public String createFileName(String filename) {
        return UUID.randomUUID().toString() + "." + getFileExtension(filename);
    }

    //파일 확장자 추출
    @Override
    public String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
