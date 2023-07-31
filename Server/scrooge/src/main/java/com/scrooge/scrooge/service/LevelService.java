package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.Member;
import com.scrooge.scrooge.dto.MemberDto;
import com.scrooge.scrooge.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final MemberRepository memberRepository;

    public MemberDto updateExpAfterDailySettlement(MemberDto memberDto) {

        Optional<Member> member =  memberRepository.findById(memberDto.getId());
        if(member.isPresent()) {
            // 경험치 +100 정산해주기
            member.get().setExp(member.get().getExp() + 100);
            Member updatedMember = memberRepository.save(member.get());
            return new MemberDto(updatedMember);
        }
        else {
            throw new NotFoundException("해당 Member가 존재하지 않습니다.");
        }

    }
}
