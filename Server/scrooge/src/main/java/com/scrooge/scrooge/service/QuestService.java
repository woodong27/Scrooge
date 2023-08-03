package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.member.MemberSelectedQuest;
import com.scrooge.scrooge.domain.Quest;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.QuestDto;
import com.scrooge.scrooge.dto.member.MemberSelectedQuestDto;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.member.MemberSelectedQuestRepository;
import com.scrooge.scrooge.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepository questRepository;
    private final MemberSelectedQuestRepository memberSelectedQuestRepository;
    private final MemberRepository memberRepository;

    public Optional<QuestDto> getQuest(Long questId) {
        return questRepository.findById(questId).map(quest -> {
            QuestDto questDto = new QuestDto();
            questDto.setId(quest.getId());
            questDto.setTitle(quest.getTitle());
            questDto.setDescription(quest.getDescription());
            questDto.setMaxCount(quest.getMaxCount());

            return questDto;
        });
    }

    public List<QuestDto> getRandomQuests() {
        List<Quest> quests = questRepository.findRandomQuests();
        return quests.stream()
                .map(QuestDto::new)
                .collect(Collectors.toList());
    }

    public void selectQuest(Long questId, String email) {

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member member = optionalMember.orElseThrow(() -> new NotFoundException("해당 멤버를 찾을 수 없습니다."));

        Optional<Quest> optionalQuest = questRepository.findById(questId);
        Quest quest = optionalQuest.orElseThrow(() -> new NotFoundException("해당 퀘스트를 찾을 수 없습니다."));

        if (member.getMemberSelectedQuests().size() < 3) {
            MemberSelectedQuest memberSelectedQuest = new MemberSelectedQuest();
            memberSelectedQuest.setMember(member);
            memberSelectedQuest.setQuest(quest);
            memberSelectedQuest.setCompleteCount(0);

            memberSelectedQuestRepository.save(memberSelectedQuest);

        } else {
            throw new RuntimeException("3개 이상의 퀘스트틀 선택할 수 없습니다.");
        }
    }

    public List<MemberSelectedQuestDto> updateCompleteCount(Long questId, Long memberId) {
        MemberSelectedQuest memberSelectedQuest = memberSelectedQuestRepository.findSelectedQuestByMemberIdAndQuestId(memberId, questId)
                .orElseThrow(() ->new NotFoundException("그런 퀘스트 없습니다."));

        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> new NotFoundException("그런 퀘스트 없다"));

        int currentCompleteCount= memberSelectedQuest.getCompleteCount();

        if (quest.getMaxCount() == currentCompleteCount+1) {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new NotFoundException(("그런 회원 없다.")));
            member.setExp(member.getExp()+200);
            memberRepository.save(member);
            memberSelectedQuestRepository.delete(memberSelectedQuest);
        } else {
            memberSelectedQuest.setCompleteCount(currentCompleteCount + 1);
            memberSelectedQuestRepository.save(memberSelectedQuest);
        }
        List<MemberSelectedQuest> memberSelectedQuests = memberSelectedQuestRepository.findMemberSelectedQuestsById(memberId);
        return memberSelectedQuests.stream()
                .map(MemberSelectedQuestDto::new)
                .collect(Collectors.toList());
    }

    public List<MemberSelectedQuestDto> getMemberSelectedQuests(Long memberId) {
        List<MemberSelectedQuest> memberSelectedQuests = memberSelectedQuestRepository.findMemberSelectedQuestsById(memberId);
        return memberSelectedQuests.stream()
                .map(MemberSelectedQuestDto::new)
                .collect(Collectors.toList());
    }
}
