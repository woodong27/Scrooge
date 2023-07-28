package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.*;
import com.scrooge.scrooge.dto.*;
import com.scrooge.scrooge.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final LevelRepository levelRepository;
    private final UserOwningAvatarRepository userOwningAvatarRepository;
    private final UserOwningBadgeRepository userOwningBadgeRepository;
    private final UserSelectedQuestRepository userSelectedQuestRepository;

    public User signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setName(signUpRequestDto.getName());
        user.setNickname(signUpRequestDto.getNickname());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(signUpRequestDto.getPassword());

        user.setExp(0);
        user.setStreak(0);
        user.setWeeklyConsum(0);
        user.setWeeklyGoal(0);
        user.setJoinedAt(LocalDateTime.now());

        Level defaultLevel = levelRepository.findById(1L).orElse(null);
        user.setLevel(defaultLevel);

        return userRepository.save(user);
    }

    public Optional<UserDto> getUserInfo(Long userId) {
        return userRepository.findWithRelatedEntitiesById(userId).map(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setNickname(user.getNickname());
            userDto.setEmail(user.getEmail());
            userDto.setExp(user.getExp());
            userDto.setStreak(user.getStreak());
            userDto.setWeeklyGoal(user.getWeeklyGoal());
            userDto.setWeeklyConsum(user.getWeeklyConsum());
            userDto.setJoinedAt(user.getJoinedAt());
            userDto.setLevel(user.getLevel());
            userDto.setMainBadge(user.getMainBadge());
            userDto.setMainAvatar(user.getMainAvatar());

            List<UserOwningAvatar> userOwningAvatars = userOwningAvatarRepository.findUserOwningAvatarsById(userId);
            userDto.setUserOwningAvatars(userOwningAvatars.stream()
                    .map(UserOwningAvatarDto::new)
                    .collect(Collectors.toList()));

            List<UserOwningBadge> userOwningBadges = userOwningBadgeRepository.findUserOwningBadgesById(userId);
            userDto.setUserOwningBadges(userOwningBadges.stream()
                    .map(UserOwningBadgeDto::new)
                    .collect(Collectors.toList()));

            List<UserSelectedQuest> userSelectedQuests = userSelectedQuestRepository.findUserSelectedQuestsById(userId);
//            System.out.println(userSelectedQuests);
            userDto.setUserSelectedQuests(userSelectedQuests.stream()
                    .map(UserSelectedQuestDto::new)
                    .collect(Collectors.toList()));

            return userDto;
        });
    }
}
