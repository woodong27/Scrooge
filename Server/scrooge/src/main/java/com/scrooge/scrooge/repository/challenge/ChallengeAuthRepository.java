package com.scrooge.scrooge.repository.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChallengeAuthRepository extends JpaRepository<ChallengeAuth, Long> {

    @Query("SELECT COUNT(ca) FROM ChallengeAuth ca WHERE ca.isSuccess = true and ca.challengeParticipant.team = 0 and ca.challengeParticipant.challenge.id = :challengeId")
    Integer countZeroSuccessCount(Long challengeId);

    @Query("SELECT COUNT(ca) FROM ChallengeAuth ca WHERE ca.isSuccess = true and ca.challengeParticipant.team = 1 and ca.challengeParticipant.challenge.id = :challengeId")
    Integer countOneSuccessCount(Long challengeId);
}
