package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.dto.UploadDto;
import com.scrooge.scrooge.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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

    @GetMapping("/{id}")
    public ResponseEntity<String> getImageUrl(@PathVariable Long id) {
        String imgAddress = uploadService.getImageAddress(id);
        if (imgAddress != null) {
            return ResponseEntity.ok(imgAddress);
        }
        return ResponseEntity.notFound().build();
    }
}
