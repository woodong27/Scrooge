package com.scrooge.scrooge.service.member;

import com.scrooge.scrooge.domain.*;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.domain.member.MemberOwningAvatar;
import com.scrooge.scrooge.domain.member.MemberOwningBadge;
import com.scrooge.scrooge.domain.member.MemberSelectedQuest;
import com.scrooge.scrooge.dto.member.*;
import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.repository.*;
import com.scrooge.scrooge.repository.member.MemberOwningAvatarRepository;
import com.scrooge.scrooge.repository.member.MemberOwningBadgeRepository;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.member.MemberSelectedQuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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

    public String login(LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("중복된 이메일 입니다."));

        if (bCryptPasswordEncoder.matches(password, member.getPassword())) {
            return jwtTokenProvider.createToken(email, member.getId());
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    }

    public void signUp(SignUpRequestDto signUpRequestDto) {
        if (memberRepository.existsMemberByEmail(signUpRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        if (!signUpRequestDto.getPassword1().equals(signUpRequestDto.getPassword2())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(signUpRequestDto.getPassword1());

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

            return memberDto;
        });
    }

    public MemberDto updateWeeklyGoal(UpdateWeeklyGoalDto updateWeeklyGoalDto, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findWithRelatedEntitiesById(memberId);
        if (optionalMember.isPresent()) {
            // 주간 목표 설정
//            member.get().setWeeklyGoal(memberDto.getWeeklyGoal());
            Member member = optionalMember.get();
            member.setWeeklyGoal(updateWeeklyGoalDto.getWeeklyGoal());
//            System.out.println(member);

            // DB에 업데이트 된 사용자 저장
//            Member updatedMember = memberRepository.save(member.get());
            return new MemberDto(member);
        }
        else {
            throw new NotFoundException(memberId + "의 ID을 가진 사용자를 찾을 수 없습니다.");
        }
    }

    public MemberDto updatePassword(UpdatePasswordDto updatePasswordDto, Long memberId) {
        Member member = memberRepository.findWithRelatedEntitiesById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));

        if (!bCryptPasswordEncoder.matches(updatePasswordDto.getCurrentPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호를 다시 입력해주세요.");
        }

        member.setPassword(bCryptPasswordEncoder.encode(updatePasswordDto.getNewPassword()));
        return new MemberDto(member);
    }
}


