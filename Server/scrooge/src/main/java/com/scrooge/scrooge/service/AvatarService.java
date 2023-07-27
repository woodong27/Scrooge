package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.Avatar;
import com.scrooge.scrooge.dto.AvatarDto;
import com.scrooge.scrooge.repository.AvatarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvatarService {

    private final AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    @Transactional(readOnly = true)
    public List<AvatarDto> getAvatars() {
        List<Avatar> avatars = avatarRepository.findAll();
        List<AvatarDto> avatarDtos = avatars.stream().map(AvatarDto::new).collect(Collectors.toList());
        return avatarDtos;
    }
}

