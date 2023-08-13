package com.scrooge.scrooge.controller.challenge;

import com.scrooge.scrooge.dto.challengeDto.ImagePaths;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImageCompareController {

    @PostMapping("/send-images")
    public ResponseEntity<Double> sendImages(@RequestBody ImagePaths imagePaths) {
        String fastApiURL = "http://127.0.0.1:8000/compare-images";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ImagePaths> requestEntity = new HttpEntity<>(imagePaths, headers);

        return restTemplate.postForEntity(fastApiURL, requestEntity, Double.class);
    }
}
