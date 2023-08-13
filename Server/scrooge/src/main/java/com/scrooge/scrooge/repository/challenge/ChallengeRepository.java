package com.scrooge.scrooge.repository.challenge;

import com.scrooge.scrooge.domain.challenge.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findAllByCategory(String category);
    List<Challenge> findAllByStatus(Integer status);

    boolean existsByIdAndChallengeMasterId(Long challengeId, Long challengeMasterId);
}
