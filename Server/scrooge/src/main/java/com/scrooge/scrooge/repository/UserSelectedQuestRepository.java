package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.UserSelectedQuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSelectedQuestRepository extends JpaRepository<UserSelectedQuest, Long> {
}
