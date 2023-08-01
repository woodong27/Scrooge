package com.scrooge.scrooge.repository.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeParticipantRepository extends JpaRepository<ChallengeParticipant, Long> {
   @Query("SELECT cp FROM ChallengeParticipant cp WHERE cp.member.id = :memberId")
    List<ChallengeParticipant> findAllByMemberId(Long memberId);
}
