package com.scrooge.scrooge.repository.challenge;

import com.scrooge.scrooge.domain.challenge.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
   @Query("SELECT ch FROM Challenge ch WHERE ch.category = :category")
    List<Challenge> findAllByCategory(String category);

    @Query("SELECT ch FROM Challenge ch WHERE ch.status = 2")
    List<Challenge> findAllByStatus2();
}
