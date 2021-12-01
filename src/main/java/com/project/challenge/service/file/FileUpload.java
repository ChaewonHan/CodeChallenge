package com.project.challenge.service.file;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileUpload {

    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String filename);

    void uploadThumbnailFile(InputStream inputStream, ObjectMetadata objectMetadata, String filename);

    String getFileUrl(String filename);

}
