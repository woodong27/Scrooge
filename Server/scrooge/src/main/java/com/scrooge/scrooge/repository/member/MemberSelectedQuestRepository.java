package com.scrooge.scrooge.repository.member;

import com.scrooge.scrooge.domain.member.MemberSelectedQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSelectedQuestRepository extends JpaRepository<MemberSelectedQuest, Long> {

    @Query("SELECT msq FROM MemberSelectedQuest msq WHERE msq.member.id = ?1")
    List<MemberSelectedQuest> findMemberSelectedQuestsById(Long memberId);
}
