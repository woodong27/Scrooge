package com.scrooge.scheduler;

import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WeeklyResetScheduler {

    private final MemberRepository memberRepository;

    @Scheduled(cron = "0 0 0 * * 2")
    public void resetWeeklyConsumAndGoal() {
        //Member 테이블의 모든 레코드를 가져와서 weekly_consum 값과 weekly_goal 값을 0으로 업데이트
        List<Member> members = memberRepository.findAll();
        for(Member member : members) {
            member.setWeeklyConsum(0);
            member.setWeeklyGoal(0);
        }

        // 업데이트 된 값 다시 레포지토리에 저장
        memberRepository.saveAll(members);
    }

}
