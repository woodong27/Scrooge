package com.scrooge.scrooge.repository.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeParticipantRepository extends JpaRepository<ChallengeParticipant, Long> {
   @Query("SELECT cp FROM ChallengeParticipant cp WHERE cp.member.id = :memberId")
    List<ChallengeParticipant> findAllByMemberId(Long memberId);

   @Query("SELECT COUNT(cp) FROM ChallengeParticipant  cp WHERE cp.challenge.id = :challengeId AND cp.team = 0")
    Integer countTeamZeroByChallengeId(Long challengeId);

   @Query("SELECT count(cp) FROM ChallengeParticipant cp WHERE cp.challenge.id = :challengeId AND cp.team = 1")
    Integer countTeamOneByChallengeId(Long challengeId);
}
