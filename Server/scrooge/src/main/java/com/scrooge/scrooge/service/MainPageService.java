package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.UserOwningAvatar;
import com.scrooge.scrooge.dto.MainPageDto;
import com.scrooge.scrooge.repository.UserOwningAvatarRepository;
import com.scrooge.scrooge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainPageService {
//    private final UserRepository userRepository;
//    private final UserOwningAvatarRepository userOwningAvatarRepository;
//
//    public Optional<MainPageDto> getUserMainPageInfo(Long userId) {
//        return userRepository.findById(userId).map(user -> {
//            MainPageDto mainPageDto = new MainPageDto();
//            mainPageDto.setNickname(user.getNickname());
//            mainPageDto.setLevelId(user.getLevel().getId());
//            mainPageDto.setExp(user.getExp());
//
//            Optional<UserOwningAvatar> mainAvatar = userOwningAvatarRepository.findMainAvatarByUserId(userId);
//            mainAvatar.ifPresent(avatar -> mainPageDto.setMainAvatarId(avatar.getAvatar().getId()));
//
//            return mainPageDto;
//        });
//    }

}