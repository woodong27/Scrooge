package com.scrooge.scrooge.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class BadgeDataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        if(checkIfInitialDataExists()) {
            insertInitialData();
        }
    }

    private boolean checkIfInitialDataExists() {
        Long count = (Long) entityManager.createQuery("SELECT COUNT(b) FROM Badge b").getSingleResult();
        return count == 0;
    }

    private void insertInitialData() {
        String[] badgeNames = {
                "출첵1", "출첵2", "출첵3",
                "챌린지 우승1", "퀘스트 성공1", "게시글 리뷰1",
                "첫 뱃지"
        };

        String[] badgeDescriptions = {
                "첫번째 일일 정산 완료", "일일 정산 7일 완료", "일일 정산 한달 완료",
                "첫번째 챌린지 우승", "첫번째모든 퀘스트 완료", "첫번째 게시글 평가",
                "첫번째 뱃지 획득을 기념하는 뱃지"
        };

        Integer[] badgeMaxCount = {
                1, 7, 30, 1, 1, 1, 1
        };

        String[] imgAddresses = {
                "assets/badge/badge1.png", "assets/badge/badge2.png", "assets/badge/badge3.png",
                "assets/badge/badge4.png", "assets/badge/badge5.png", "assets/badge/badge6.png",
                "assets/badge/badge7.png"
        };

        for (int i = 0; i < badgeNames.length; i++) {
            String insertQuery = "INSERT INTO badge (badge_name, badge_description, img_address, max_count) VALUES " +
                    "('" + badgeNames[i] + "', '" + badgeDescriptions[i] + "', '" + imgAddresses[i] + "' , '" + badgeMaxCount[i] + "');";
            entityManager.createNativeQuery(insertQuery).executeUpdate();
        }
    }
}
