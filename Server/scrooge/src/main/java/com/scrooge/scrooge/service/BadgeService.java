package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.Badge;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.domain.member.MemberOwningBadge;
import com.scrooge.scrooge.dto.BadgeDto;
import com.scrooge.scrooge.repository.BadgeRepository;
import com.scrooge.scrooge.repository.member.MemberOwningBadgeRepository;
import com.scrooge.scrooge.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final MemberOwningBadgeRepository memberOwningBadgeRepository;
    private final MemberRepository memberRepository;

    public List<BadgeDto> getBadges() {
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream()
                .map(BadgeDto::new)
                .collect(Collectors.toList());
    }

    public BadgeDto selectMainBadge(Long badgeId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 멤버를 찾을 수 없습니다."));

        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new NotFoundException("해당 뱃지를 찾을 수 없습니다."));

        member.setMainBadge(badge);
        memberRepository.save(member);

        return new BadgeDto(badge);
    }
}
