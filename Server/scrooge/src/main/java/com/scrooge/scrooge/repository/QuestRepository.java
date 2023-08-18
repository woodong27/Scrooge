package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

    @Query(value = "SELECT * FROM quest ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Quest> findRandomQuests();
}
