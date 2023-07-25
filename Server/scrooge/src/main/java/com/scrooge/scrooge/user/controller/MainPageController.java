package com.scrooge.scrooge.user.controller;

import com.scrooge.scrooge.user.domain.User;
import com.scrooge.scrooge.user.domain.UserOwningAvatar;
import com.scrooge.scrooge.user.dto.MainPageDTO;
import com.scrooge.scrooge.user.repository.UserOwningAvatarRepository;
import com.scrooge.scrooge.user.repository.UserRepository;
import com.scrooge.scrooge.user.service.MainPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/main")
public class MainPageController {
    private final MainPageService mainPageService;

    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MainPageDTO> getUserMainPageInfo(@PathVariable Long userId) {
        Optional<MainPageDTO> mainPageDTO = mainPageService.getUserMainPageInfo(userId);
        return mainPageDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
