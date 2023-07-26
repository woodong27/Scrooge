package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.dto.MainPageDto;
import com.scrooge.scrooge.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

    @GetMapping("/{userId}")
    public ResponseEntity<MainPageDto> getUserMainPageInfo(@PathVariable("userId") Long userId) {
        Optional<MainPageDto> mainPageDto = mainPageService.getUserMainPageInfo(userId);
        return mainPageDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
