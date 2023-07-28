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

    @Transactional(readOnly = true)
    public AvatarDto getAvatarById(Long id) {
        Avatar avatar = avatarRepository.findById(id).orElse(null);
        if (avatar != null) {
            return new AvatarDto(avatar);
        } else {
            // 아바타가 존재하지 않을 경우, 예외처리 등을 수행할 수도 있습니다.
            // 여기서는 null을 반환합니다.
            return null;
        }
    }
}

