package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}
