package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.Badge;
import com.scrooge.scrooge.domain.member.MemberOwningBadge;
import com.scrooge.scrooge.domain.member.MemberSelectedQuest;
import com.scrooge.scrooge.domain.Quest;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.QuestDto;
import com.scrooge.scrooge.dto.member.MemberSelectedQuestDto;
import com.scrooge.scrooge.repository.BadgeRepository;
import com.scrooge.scrooge.repository.member.MemberOwningBadgeRepository;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.member.MemberSelectedQuestRepository;
import com.scrooge.scrooge.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepository questRepository;
    private final MemberSelectedQuestRepository memberSelectedQuestRepository;
    private final MemberRepository memberRepository;
    private final MemberOwningBadgeRepository memberOwningBadgeRepository;
    private final BadgeService badgeService;

    public QuestDto getQuest(Long questId) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> new NotFoundException("해당 퀘스트를 찾을 수 업습니다."));

        return new QuestDto(quest);
    }

    public List<MemberSelectedQuestDto> giveRandomQuests(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 멤버를 찾을 수 없습니다."));

        List<Quest> quests = questRepository.findRandomQuests();
        for (Quest quest : quests) {
            MemberSelectedQuest memberSelectedQuest = new MemberSelectedQuest();
            memberSelectedQuest.setQuest(quest);
            memberSelectedQuest.setMember(member);
            memberSelectedQuestRepository.save(memberSelectedQuest);
        }

        return memberSelectedQuestRepository.findByMemberId(memberId).stream()
                .map(MemberSelectedQuestDto::new)
                .collect(Collectors.toList());
    }

    public void selectQuest(Long questId, Long memberId) {

        MemberSelectedQuest memberSelectedQuest = memberSelectedQuestRepository.findByMemberIdAndQuestId(memberId, questId);

        if (memberSelectedQuestRepository.countByMemberIdAndIsSelected(memberId, true) < 3) {
            memberSelectedQuest.setSelected(true);
            memberSelectedQuestRepository.save(memberSelectedQuest);
        } else {
            throw new RuntimeException("3개 이상의 퀘스트를 선택할 수 없습니다.");
        }
    }

    public void completeQuest(Long questId, Long memberId) {
        MemberSelectedQuest memberSelectedQuest = memberSelectedQuestRepository.findByMemberIdAndQuestId(memberId, questId);

        if (memberSelectedQuest.isDone()) {
            return ;
        }

        Quest quest = questRepository.findById(questId).orElseThrow(() -> new NotFoundException("그런 퀘스트 없다."));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("그런 사람 없다."));
        int currentCompleteCount = memberSelectedQuest.getCompleteCount();

        if (quest.getMaxCount() == currentCompleteCount + 1) {
            member.setExp(member.getExp() + 200);
            memberRepository.save(member);
            memberSelectedQuest.setCompleteCount(currentCompleteCount + 1);
            memberSelectedQuest.setDone(true);
            memberSelectedQuestRepository.save(memberSelectedQuest);
        } else {
            memberSelectedQuest.setCompleteCount(currentCompleteCount + 1);
            memberSelectedQuestRepository.save(memberSelectedQuest);
        }

        if (!memberSelectedQuestRepository.existsByMemberIdAndIsDoneIsFalse(memberId)
            || !memberOwningBadgeRepository.existsByBadgeIdAndMemberId(5L, memberId)) {
            badgeService.giveBadge(5L, memberId);
        }
    }

    public List<MemberSelectedQuestDto> getMemberSelectedQuests(Long memberId) {
        List<MemberSelectedQuest> memberSelectedQuests = memberSelectedQuestRepository.findByMemberIdAndIsSelected(memberId, true);
        return memberSelectedQuests.stream()
                .map(MemberSelectedQuestDto::new)
                .collect(Collectors.toList());
    }
}
