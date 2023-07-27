package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.UserSelectedQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSelectedQuestRepository extends JpaRepository<UserSelectedQuest, Long> {

    @Query("SELECT usq FROM UserSelectedQuest usq WHERE usq.user.id = ?1")
    List<UserSelectedQuest> findUserSelectedQuestsById(Long userId);
}
