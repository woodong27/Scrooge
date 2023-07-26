package com.scrooge.scrooge.service;

import com.scrooge.scrooge.dto.MainPageDto;
import com.scrooge.scrooge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainPageService {
    private final UserRepository userRepository;

    public Optional<MainPageDto> getUserMainPageInfo(Long userId) {
        return userRepository.findById(userId).map(user -> {
            MainPageDto mainPageDto = new MainPageDto();
            mainPageDto.setNickname(user.getNickname());
            mainPageDto.setLevelId(user.getLevel().getId());
            mainPageDto.setExp(user.getExp());
            return mainPageDto;
        });
    }
}
