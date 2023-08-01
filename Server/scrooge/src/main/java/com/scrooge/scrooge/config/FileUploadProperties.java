package com.scrooge.scrooge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUploadProperties {

    @Value("${file.upload.location}")
    private String uploadLocation;

    public String getUploadLocation() {
        return uploadLocation;
    }

}
