package com.project.challenge.service.file;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public interface FileService {

    public String uploadImg(MultipartFile file);

    public String uploadThumbnail(MultipartFile file);

    public String createFileName(String filename);

    public String getFileExtension(String filename);
}
