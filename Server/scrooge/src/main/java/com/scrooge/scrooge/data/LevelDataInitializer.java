package com.scrooge.scrooge.data;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LevelDataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional // 이니셜라이저에서 트랜잭션을 사용하여 데이터베이스에 저장합니다.
    public void run(String... args) {
        // 30개의 초기 데이터를 저장하는 작업 수행
        if(checkIfInitialDataExists()) {
            insertInitialData();
        }
    }

    private boolean checkIfInitialDataExists() {
        Long count = (Long) entityManager.createQuery("SELECT COUNT(l) FROM Level l").getSingleResult();
        return count == 0;
    }

    private void insertInitialData() {
        // 50개의 초기 데이터를 삽입하는 쿼리문
        String insertQuery =
                "INSERT INTO level (level, required_exp, gacha) VALUES " +
                        "(1, 500, 2), (2, 500, 2), (3, 500, 2), (4, 500, 2), (5, 500, 2), " +
                        "(6, 600, 2), (7, 600, 2), (8, 600, 2), (9, 600, 2), (10, 600, 2), " +
                        "(11, 700, 3), (12, 700, 3), (13, 700, 3), (14, 700, 3), (15, 700, 3), " +
                        "(16, 800, 3), (17, 800, 3), (18, 800, 3), (19, 800, 3), (20, 800, 3), " +
                        "(21, 900, 4), (22, 900, 4), (23, 900, 4), (24, 900, 4), (25, 900, 4), " +
                        "(26, 1000, 4), (27, 1000, 4), (28, 1000, 4), (29, 1000, 4), (30, 1000, 4)";

        entityManager.createNativeQuery(insertQuery).executeUpdate();
    }
}
