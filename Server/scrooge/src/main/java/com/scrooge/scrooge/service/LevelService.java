package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.Level;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.repository.LevelRepository;
import com.scrooge.scrooge.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final MemberRepository memberRepository;
    private final LevelRepository levelRepository;

    // 레벨업 하는 메서드
    public void levelUp(Long memberId) {
        /* 여기서 member 경험치가 증가했을 때 레벨업 조건인지 확인하기 */
        // 0. memberId에 해당하는 member 를 가져온다.
        Optional<Member> member = memberRepository.findById(memberId);

        if(member.isPresent()) {
            // 1. member의 레벨의 조건과 현재 사용자의 경험치를 비교한다.
            Integer memberLevelLimit = member.get().getLevel().getRequiredExp();
            Integer curMemberExp = member.get().getExp();

            // 2. 만약 현재 경험치가 레벨 조건보다 크거나 같으면 레벨업을 진행한다.
            if(curMemberExp >= memberLevelLimit) {
                Long newLevelId = member.get().getLevel().getId() + 1;
                Optional<Level> newLevel = levelRepository.findById(newLevelId);
                member.get().setLevel(newLevel.orElse(null));

                // 2-1. 여기서 경험치 처리도 하기
                Integer remainExp = curMemberExp - memberLevelLimit;
                member.get().setExp(remainExp);

                memberRepository.save(member.get());
            }


        }
        else {
            throw new NotFoundException("해당 Member가 존재하지 않습니다.");
        }







    }

}
