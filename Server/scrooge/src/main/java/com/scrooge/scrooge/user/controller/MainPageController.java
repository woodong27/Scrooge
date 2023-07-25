package com.scrooge.scrooge.user.controller;

import com.scrooge.scrooge.user.domain.User;
import com.scrooge.scrooge.user.domain.UserOwningAvatar;
import com.scrooge.scrooge.user.dto.MainPageDto;
import com.scrooge.scrooge.user.repository.UserOwningAvatarRepository;
import com.scrooge.scrooge.user.repository.UserRepository;
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
    public MainPageDto getMainPage(@PathVariable Long id) {
        User user = userRepository.findById(id);
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        UserOwningAvatar userOwningAvatar = userOwningAvatarRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("UserOwningAvatar not found for user with id " + user.getId()));

        return new MainPageDto(user.getNickname(), user.getLevelId(), user.getExp(), userOwningAvatar.getIsMainAvatar());
    }
}
