package com.scrooge.scrooge.service;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.scrooge.scrooge.dto.UploadDto;
import com.scrooge.scrooge.domain.Upload;
import com.scrooge.scrooge.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final Storage storage;
    private final UploadRepository uploadRepository;
    private String bucketName = "scroogestorage";

    public void uploadImage(MultipartFile img) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String ext = img.getContentType();


        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uuid)
                .setContentType(ext)
                .build();

        storage.create(blobInfo, img.getInputStream());

        String imgAddress = "https://storage.googleapis.com/" + bucketName + "/" + uuid;
        Upload upload = new Upload();
        upload.setImgAddress(imgAddress);
        uploadRepository.save(upload);
    }

    public String getImageAddress(Long id) {
        Optional<Upload> uploadOptional = uploadRepository.findById(id);
        return uploadOptional.map(Upload::getImgAddress).orElse(null);
    }
}
