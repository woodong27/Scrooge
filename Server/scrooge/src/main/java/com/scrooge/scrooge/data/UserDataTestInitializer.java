package com.scrooge.scrooge.data;

import com.scrooge.scrooge.domain.*;
import com.scrooge.scrooge.repository.*;
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
    private final UserOwningBadgeRepository userOwningBadgeRepository;
    private final BadgeRepository badgeRepository;
    private final UserSelectedQuestRepository userSelectedQuestRepository;
    private final QuestRepository questRepository;

    @Override
    public void run(String... args) {

        Level level = new Level();
        level.setLevel(1);
        level.setRequiredExp(100);
        level.setGacha(1);
        level = levelRepository.save(level);

        Avatar avatar = new Avatar();
        avatar.setName("test_avatar");
        avatar.setImgAddress("/static/assets/sample_avatar.png");
        avatar = avatarRepository.save(avatar);

        Badge badge = new Badge();
        badge.setBadgeName("test_badge");
        badge.setBadgeDescription("badge for test");
        badge.setImgAddress("test_address");
        badge = badgeRepository.save(badge);

        Quest quest = new Quest();
        quest.setTitle("test_quest");
        quest.setDescription("quest for test");
        quest.setMaxCount(3);
        quest = questRepository.save(quest);

        User user = new User();
        user.setName("test_user");
        user.setNickname("testUser");
        user.setEmail("test@example.com");
        user.setPassword("pass123");
        user.setExp(0);
        user.setStreak(0);

        // 메인 뱃지, 메인 아바타 설정
        user.setMainAvatar(avatar);
        user.setMainBadge(badge);

        //테스트용 더미 주간 목표+현재 소비량
        user.setWeeklyConsum(270000);
        user.setWeeklyGoal(400000);

        user.setJoinedAt(LocalDateTime.now());
        user.setLevel(level);
        user = userRepository.save(user);

        UserOwningAvatar userOwningAvatar = new UserOwningAvatar();
        userOwningAvatar.setAcquiredAt(LocalDateTime.now());
//        userOwningAvatar.setIsMainAvatar(true);
        userOwningAvatar.setUser(user);
        userOwningAvatar.setAvatar(avatar);
        userOwningAvatarRepository.save(userOwningAvatar);

        UserOwningBadge userOwningBadge = new UserOwningBadge();
        userOwningBadge.setUser(user);
        userOwningBadge.setBadge(badge);
        userOwningBadge.setAcquiredAt(LocalDateTime.now());
        userOwningBadgeRepository.save(userOwningBadge);

        UserSelectedQuest userSelectedQuest = new UserSelectedQuest();
        userSelectedQuest.setUser(user);
        userSelectedQuest.setQuest(quest);
        userSelectedQuest.setCompleteCount(0);
        userSelectedQuestRepository.save(userSelectedQuest);
    }
}


