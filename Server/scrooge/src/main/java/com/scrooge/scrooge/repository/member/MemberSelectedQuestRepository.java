package com.scrooge.scrooge.repository.member;

import com.scrooge.scrooge.domain.member.MemberSelectedQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberSelectedQuestRepository extends JpaRepository<MemberSelectedQuest, Long> {

    @Query("SELECT msq FROM MemberSelectedQuest msq WHERE msq.member.id = ?1")
    List<MemberSelectedQuest> findMemberSelectedQuestsById(Long memberId);

    @Query("SELECT msq FROM MemberSelectedQuest msq WHERE msq.member.id = :memberId and msq.quest.id = :questId")
    Optional<MemberSelectedQuest> findSelectedQuestByMemberIdAndQuestId(Long memberId, Long questId);

    boolean existsByMemberIdAndQuestId(Long memberId, Long questId);

    boolean existsByMemberIdAndIsDoneIsFalse(Long memberId);
}
