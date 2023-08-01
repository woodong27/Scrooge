package com.scrooge.scrooge.repository.challenge;

import com.scrooge.scrooge.domain.challenge.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
