package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.*;
import com.scrooge.scrooge.dto.*;
import com.scrooge.scrooge.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final LevelRepository levelRepository;
    private final MemberOwningAvatarRepository memberOwningAvatarRepository;
    private final MemberOwningBadgeRepository memberOwningBadgeRepository;
    private final MemberSelectedQuestRepository memberSelectedQuestRepository;

    public Member signUp(SignUpRequestDto signUpRequestDto) {
        Member user = new Member();
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

        return memberRepository.save(user);
    }

    public Optional<MemberDto> getUserInfo(Long memberId) {
        return memberRepository.findWithRelatedEntitiesById(memberId).map(user -> {
            MemberDto userDto = new MemberDto();
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

            List<MemberOwningAvatar> userOwningAvatars = memberOwningAvatarRepository.findMemberOwningAvatarsById(memberId);
            userDto.setMemberOwningAvatars(userOwningAvatars.stream()
                    .map(MemberOwningAvatarDto::new)
                    .collect(Collectors.toList()));

            List<MemberOwningBadge> userOwningBadges = memberOwningBadgeRepository.findMemberOwningBadgesById(memberId);
            userDto.setMemberOwningBadges(userOwningBadges.stream()
                    .map(MemberOwningBadgeDto::new)
                    .collect(Collectors.toList()));

            List<MemberSelectedQuest> userSelectedQuests = memberSelectedQuestRepository.findMemberSelectedQuestsById(memberId);
//            System.out.println(userSelectedQuests);
            userDto.setMemberSelectedQuests(userSelectedQuests.stream()
                    .map(MemberSelectedQuestDto::new)
                    .collect(Collectors.toList()));

            return userDto;
        });
    }
}
