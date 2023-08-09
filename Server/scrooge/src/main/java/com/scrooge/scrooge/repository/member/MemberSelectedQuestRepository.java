package com.scrooge.scrooge.repository.member;

import com.scrooge.scrooge.domain.member.MemberSelectedQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSelectedQuestRepository extends JpaRepository<MemberSelectedQuest, Long> {

    boolean existsByMemberIdAndQuestId(Long memberId, Long questId);

    boolean existsByMemberIdAndIsDoneIsFalse(Long memberId);

    List<MemberSelectedQuest> findByMemberId(Long memberId);

    MemberSelectedQuest findByMemberIdAndQuestId(Long memberId, Long questId);

    Integer countByMemberIdAndIsSelected(Long memberId, boolean isSelected);

    List<MemberSelectedQuest> findByMemberIdAndIsSelected(Long memberId, boolean isSelected);

    boolean existsByMemberId(Long memberId);
}
