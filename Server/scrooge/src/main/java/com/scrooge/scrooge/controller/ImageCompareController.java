package com.scrooge.scrooge.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImageCompareController {

    @GetMapping("/compare-images/{img1}/{img2}")
    public ResponseEntity<Double> compareImages(@PathVariable("img1")String img1, @PathVariable("img2")String img2) {
        String FAST_API_URL = "http://localhost:8000/compare-images"+img1+"/"+img2;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> imageURLs = new HashMap<>();
        imageURLs.put("imagePath1", img1);
        imageURLs.put("imagePath2", img2);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(imageURLs, headers);

        ResponseEntity<Double> result = restTemplate.postForEntity(FAST_API_URL, requestEntity, Double.class);

        System.out.println(result);
        return result;
    }
}
