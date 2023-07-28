package com.scrooge.scrooge.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
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
        // 50개의 초기 데이터를 저장하는 작업 수행
        insertInitialData();
    }

    private void insertInitialData() {
        // 50개의 초기 데이터를 삽입하는 쿼리문
        String insertQuery =
                "INSERT INTO level (level, required_exp, gacha) VALUES " +
                        "(1, 100, 2), (2, 100, 2), (3, 100, 2), (4, 100, 2), (5, 100, 2), " +
                        "(6, 150, 2), (7, 150, 2), (8, 150, 2), (9, 150, 2), (10, 150, 2), " +
                        "(11, 200, 3), (12, 200, 3), (13, 200, 3), (14, 200, 3), (15, 200, 3), " +
                        "(16, 250, 3), (17, 250, 3), (18, 250, 3), (19, 250, 3), (20, 250, 3), " +
                        "(21, 300, 4), (22, 300, 4), (23, 300, 4), (24, 300, 4), (25, 300, 4), " +
                        "(26, 350, 4), (27, 350, 4), (28, 350, 4), (29, 350, 4), (30, 350, 4), " +
                        "(31, 400, 5), (32, 400, 5), (33, 400, 5), (34, 400, 5), (35, 400, 5), " +
                        "(36, 450, 5), (37, 450, 5), (38, 450, 5), (39, 450, 5), (40, 450, 5), " +
                        "(41, 500, 6), (42, 500, 6), (43, 500, 6), (44, 500, 6), (45, 500, 6), " +
                        "(46, 550, 6), (47, 550, 6), (48, 550, 6), (49, 550, 6), (50, 550, 6);";

        entityManager.createNativeQuery(insertQuery).executeUpdate();
    }
}
