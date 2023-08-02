package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.Avatar;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.AvatarDto;
import com.scrooge.scrooge.repository.AvatarRepository;
import com.scrooge.scrooge.repository.member.MemberOwningAvatarRepository;
import com.scrooge.scrooge.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private final AvatarRepository avatarRepository;
    private final MemberOwningAvatarRepository memberOwningAvatarRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<AvatarDto> getAvatars() {
        List<Avatar> avatars = avatarRepository.findAll();
        return avatars.stream()
                .map(AvatarDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AvatarDto getAvatarById(Long avatarId) {
        Avatar avatar = avatarRepository.findById(avatarId)
                .orElseThrow(() -> new NotFoundException("그런 아바타 없습니다."));

        return new AvatarDto(avatar);
    }

    public AvatarDto selectMainAvatar(Long avatarId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("그런 유저 없습니다."));

        Avatar avatar = avatarRepository.findById(avatarId)
                .orElseThrow(() -> new NotFoundException("그런 아바타 없습니다."));

        member.setMainAvatar(avatar);
        memberRepository.save(member);

        return new AvatarDto(avatar);
    }
}

