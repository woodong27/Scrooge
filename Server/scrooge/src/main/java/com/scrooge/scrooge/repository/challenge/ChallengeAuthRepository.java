package com.scrooge.scrooge.repository.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeAuthRepository extends JpaRepository<ChallengeAuth, Long> {

    // 팀 0의 전체 성공 횟수
    @Query("SELECT COUNT(ca) FROM ChallengeAuth ca WHERE ca.isSuccess = true and ca.challengeParticipant.team = 0 and ca.challengeParticipant.challenge.id = :challengeId")
    Integer countZeroSuccessCount(Long challengeId);

    // 팀 1의 전체 성공 횟수
    @Query("SELECT COUNT(ca) FROM ChallengeAuth ca WHERE ca.isSuccess = true and ca.challengeParticipant.team = 1 and ca.challengeParticipant.challenge.id = :challengeId")
    Integer countOneSuccessCount(Long challengeId);

    // challengeParticipantId를 통해 인증 성공 횟수 가져오기
    @Query("SELECT COUNT(ca) FROM ChallengeAuth ca WHERE ca.challengeParticipant.id = :challengeParticipantId AND ca.isSuccess = true")
    Integer countSuccessCountByChallengeParticipantId(Long challengeParticipantId);

    // challengeParticipantId에 맞는 인증 데이터 모두 가져오기
    @Query("SELECT ca FROM ChallengeAuth ca WHERE ca.challengeParticipant.id = :challengeParticipantId")
    List<ChallengeAuth> findAllByChallengeParticipantId(Long challengeParticipantId);
}
