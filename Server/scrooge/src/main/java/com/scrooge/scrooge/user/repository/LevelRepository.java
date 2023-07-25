package com.scrooge.scrooge.user.repository;

import com.scrooge.scrooge.user.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Long> {
    
}
