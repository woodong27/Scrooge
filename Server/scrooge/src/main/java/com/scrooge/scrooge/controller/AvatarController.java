package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.dto.AvatarDto;
import com.scrooge.scrooge.service.AvatarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping("/avatars")
    public List<AvatarDto> getAvatars() {
        List<AvatarDto> avatars = avatarService.getAvatars();
        return avatars;
    }

    @GetMapping("/avatars/{id}")
    public AvatarDto getAvatarById(@PathVariable Long id) {
        AvatarDto avatar = avatarService.getAvatarById(id);
        return avatar;
    }
}
