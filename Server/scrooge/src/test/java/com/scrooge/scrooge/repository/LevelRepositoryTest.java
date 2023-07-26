package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Level;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LevelRepositoryTest {

    @Autowired
    LevelRepository levelRepository;

    @Test
    public void createLevel() {
        Level level = new Level();
        level.setLevel(1);
        level.setRequiredExp(100);
        level.setGacha(5);
        levelRepository.save(level);

        Level foundLevel = levelRepository.findById(level.getId()).orElse(null);
        assertNotNull(foundLevel);
        assertEquals(level.getLevel(), foundLevel.getLevel());
        assertEquals(level.getRequiredExp(), foundLevel.getRequiredExp());
        assertEquals(level.getGacha(), foundLevel.getGacha());
    }
}
