package com.scrooge.scrooge.repository.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeExampleImageRepository extends JpaRepository<ChallengeExampleImage, Long> {
    @Query("SELECT ce FROM ChallengeExampleImage ce WHERE ce.challenge.id = :challengeId")
    List<ChallengeExampleImage> findByChallengeId(Long challengeId);
}
