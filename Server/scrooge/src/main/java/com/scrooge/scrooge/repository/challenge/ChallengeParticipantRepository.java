package com.scrooge.scrooge.repository.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeParticipantRepository extends JpaRepository<ChallengeParticipant, Long> {
   
    // memberId에 해당하는 challengeParticipant 전체 조회
    List<ChallengeParticipant> findAllByMemberId(Long memberId);

    // challengeId에 해당하는 team0의 수
   @Query("SELECT COUNT(cp) FROM ChallengeParticipant  cp WHERE cp.challenge.id = :challengeId AND cp.team = 0")
    Integer countTeamZeroByChallengeId(@Param("challengeId")Long challengeId);

    // challengeId에 해당하는 team1의 수
   @Query("SELECT count(cp) FROM ChallengeParticipant cp WHERE cp.challenge.id = :challengeId AND cp.team = 1")
    Integer countTeamOneByChallengeId(@Param("challengeId")Long challengeId);

   // memberId와 challengeId로 challengeParticipant 찾기
    ChallengeParticipant findByMemberIdAndChallengeId(Long memberId, Long challengeId);
}
