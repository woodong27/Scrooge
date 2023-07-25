package com.scrooge.scrooge.user.controller;

import com.scrooge.scrooge.user.domain.UserOwningAvatar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/main")
public class MainPageController {
    private final UserRepository userRepository;
    private final UserOwningAvatarRepository userOwningAvatarRepository;

    public MainPageController(UserRepository userRepository, UserOwningAvatarRepository userOwningAvatarRepository) {
        this.userRepository = userRepository;
        this.userOwningAvatarRepository = userOwningAvatarRepository;
    }

    @GetMapping
    public MainPageDto getMainPage(@PathVariable BigInteger id) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        UserOwningAvatar userOwningAvatar = userOwningAvatarRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("UserOwningAvatar not found for user with id " + userId));

        return new MainPageDto(user.getNickname(), user.getLevelId(), user.getExp(), userOwningAvatar.getIsMainAvatar());
    }
}
