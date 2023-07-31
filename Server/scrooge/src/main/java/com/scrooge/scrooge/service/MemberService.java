package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.*;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.*;
import com.scrooge.scrooge.jwt.JwtTokenProvider;
import com.scrooge.scrooge.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

//    public Member signUp(SignUpRequestDto signUpRequestDto) {
//        Member member = new Member();
//        member.setName(signUpRequestDto.getName());
//        member.setNickname(signUpRequestDto.getNickname());
//        member.setEmail(signUpRequestDto.getEmail());
//
//        member.setPassword(bCryptPasswordEncoder.encode(signUpRequestDto.getPassword()));
//
//        member.setExp(0);
//        member.setStreak(0);
//        member.setWeeklyConsum(0);
//        member.setWeeklyGoal(0);
//        member.setJoinedAt(LocalDateTime.now());
//
//        Level defaultLevel = levelRepository.findById(1L).orElse(null);
//        member.setLevel(defaultLevel);
//
//        return memberRepository.save(member);
//    }

    public String login(LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("중복된 이메일 입니다."));

        if (bCryptPasswordEncoder.matches(password, member.getPassword())) {
            return jwtTokenProvider.createToken(email);
        } else {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

    }


    public void signUp(SignUpRequestDto signUpRequestDto) {
        if (memberRepository.existsMemberByEmail(signUpRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(signUpRequestDto.getPassword());

        Member member = new Member();
        member.setName(signUpRequestDto.getName());
        member.setNickname(signUpRequestDto.getNickname());
        member.setEmail(signUpRequestDto.getEmail());
        member.setPassword(encodedPassword);

        // 기본 값
        member.setExp(0);
        member.setStreak(0);
        member.setWeeklyConsum(0);
        member.setWeeklyGoal(0);
        member.setJoinedAt(LocalDateTime.now());

        Level defaultLevel = levelRepository.findById(1L).orElse(null);
        member.setLevel(defaultLevel);

        memberRepository.save(member);
    }
//
//    public Optional<MemberDto> getMemberInfo(Long memberId) {
//        return memberRepository.findWithRelatedEntitiesById(memberId).map(user -> {
//            MemberDto userDto = new MemberDto();
//            userDto.setId(user.getId());
//            userDto.setName(user.getName());
//            userDto.setNickname(user.getNickname());
//            userDto.setEmail(user.getEmail());
//            userDto.setExp(user.getExp());
//            userDto.setStreak(user.getStreak());
//            userDto.setWeeklyGoal(user.getWeeklyGoal());
//            userDto.setWeeklyConsum(user.getWeeklyConsum());
//            userDto.setJoinedAt(user.getJoinedAt());
//            userDto.setLevel(user.getLevel());
//            userDto.setMainBadge(user.getMainBadge());
//            userDto.setMainAvatar(user.getMainAvatar());
//
//            List<MemberOwningAvatar> userOwningAvatars = memberOwningAvatarRepository.findMemberOwningAvatarsById(memberId);
//            userDto.setMemberOwningAvatars(userOwningAvatars.stream()
//                    .map(MemberOwningAvatarDto::new)
//                    .collect(Collectors.toList()));
//
//            List<MemberOwningBadge> userOwningBadges = memberOwningBadgeRepository.findMemberOwningBadgesById(memberId);
//            userDto.setMemberOwningBadges(userOwningBadges.stream()
//                    .map(MemberOwningBadgeDto::new)
//                    .collect(Collectors.toList()));
//
//            List<MemberSelectedQuest> userSelectedQuests = memberSelectedQuestRepository.findMemberSelectedQuestsById(memberId);
////            System.out.println(userSelectedQuests);
//            userDto.setMemberSelectedQuests(userSelectedQuests.stream()
//                    .map(MemberSelectedQuestDto::new)
//                    .collect(Collectors.toList()));
//
//            return userDto;
//        });
//    }

    public Optional<MemberDto> getInfo(String email) {

        return memberRepository.findWithRelatedEntitiesByEmail(email).map(member -> {
            MemberDto memberDto = new MemberDto();
            memberDto.setId(member.getId());
            memberDto.setName(member.getName());
            memberDto.setNickname(member.getNickname());
            memberDto.setEmail(member.getEmail());
            memberDto.setExp(member.getExp());
            memberDto.setStreak(member.getStreak());
            memberDto.setWeeklyGoal(member.getWeeklyGoal());
            memberDto.setWeeklyConsum(member.getWeeklyConsum());
            memberDto.setJoinedAt(member.getJoinedAt());
            memberDto.setLevel(member.getLevel());
            memberDto.setMainBadge(member.getMainBadge());
            memberDto.setMainAvatar(member.getMainAvatar());

            List<MemberOwningAvatar> userOwningAvatars = memberOwningAvatarRepository.findMemberOwningAvatarsById(member.getId());
            memberDto.setMemberOwningAvatars(userOwningAvatars.stream()
                    .map(MemberOwningAvatarDto::new)
                    .collect(Collectors.toList()));

            List<MemberOwningBadge> userOwningBadges = memberOwningBadgeRepository.findMemberOwningBadgesById(member.getId());
            memberDto.setMemberOwningBadges(userOwningBadges.stream()
                    .map(MemberOwningBadgeDto::new)
                    .collect(Collectors.toList()));

            List<MemberSelectedQuest> userSelectedQuests = memberSelectedQuestRepository.findMemberSelectedQuestsById(member.getId());
        //           System.out.println(userSelectedQuests);
            memberDto.setMemberSelectedQuests(userSelectedQuests.stream()
                    .map(MemberSelectedQuestDto::new)
                    .collect(Collectors.toList()));

            return memberDto;
        });
    }

    private String extractToken (String token){
        if (token != null && token.startsWith("Bearer")) {
            return token.substring(7);
        }
        return null;
    }
}


