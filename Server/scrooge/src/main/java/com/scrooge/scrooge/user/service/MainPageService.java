package com.scrooge.scrooge.user.service;

import com.scrooge.scrooge.user.dto.MainPageDTO;
import com.scrooge.scrooge.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainPageService {
    private final UserRepository userRepository;

    public MainPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<MainPageDTO> getUserMainPageInfo(Long userId) {
        return userRepository.findById(userId).map(user -> {
            MainPageDTO mainPageDTO = new MainPageDTO();
            mainPageDTO.setNickname(user.getNickname());
            mainPageDTO.setLevel(user.getLevel().getLevel());
            mainPageDTO.setExp(user.getExp());
            return mainPageDTO;
        });
    }
}
