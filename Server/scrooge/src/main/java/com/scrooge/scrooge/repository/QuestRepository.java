package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}
