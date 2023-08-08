package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.dto.UploadDto;
import com.scrooge.scrooge.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping(consumes="multipart/form-data")
    public ResponseEntity<?> updateUpload(@RequestParam MultipartFile img) throws IOException {

        uploadService.uploadImage(img);

        return new ResponseEntity(HttpStatus.OK);
    }
}
