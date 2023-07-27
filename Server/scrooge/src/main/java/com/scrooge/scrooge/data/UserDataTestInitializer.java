package com.scrooge.scrooge.data;

import com.scrooge.scrooge.domain.Avatar;
import com.scrooge.scrooge.domain.Level;
import com.scrooge.scrooge.domain.User;
import com.scrooge.scrooge.domain.UserOwningAvatar;
import com.scrooge.scrooge.repository.AvatarRepository;
import com.scrooge.scrooge.repository.LevelRepository;
import com.scrooge.scrooge.repository.UserOwningAvatarRepository;
import com.scrooge.scrooge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Transactional
public class UserDataTestInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final LevelRepository levelRepository;
    private final UserOwningAvatarRepository userOwningAvatarRepository;
    private final AvatarRepository avatarRepository;

    @Override
    public void run(String... args) {

        Level level = new Level();
        level.setLevel(1);
        level.setRequiredExp(100);
        level.setGacha(1);
        level = levelRepository.save(level);

        Avatar avatar = new Avatar();
        avatar.setName("test_avatar");
        avatar.setImgAddress("test_address");
        avatar = avatarRepository.save(avatar);

        User user = new User();
        user.setName("test");
        user.setNickname("test");
        user.setEmail("test@example.com");
        user.setPassword("pass123");
        user.setExp(0);
        user.setStreak(0);

        //테스트용 더미 주간 목표+현재 소비량
        user.setWeeklyConsum(270000);
        user.setWeeklyGoal(400000);

        user.setJoinedAt(LocalDateTime.now());
        user.setLevel(level);
        user = userRepository.save(user);

        UserOwningAvatar userOwningAvatar = new UserOwningAvatar();
        userOwningAvatar.setId(1L);
        userOwningAvatar.setAcquiredAt(LocalDateTime.now());
        userOwningAvatar.setIsMainAvatar(true);
        userOwningAvatar.setUser(user);
        userOwningAvatar.setAvatar(avatar);
        userOwningAvatarRepository.save(userOwningAvatar);
    }

}
