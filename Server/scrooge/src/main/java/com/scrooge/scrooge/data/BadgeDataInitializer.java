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
        insertInitialData();
    }

    private void insertInitialData() {
        String[] badgeNames = {
                "badge1", "badge2", "badge3",
                "badge4", "badge5", "badge6"
        };

        String[] badgeDescriptions = {
                "7일 연속 접속 시 부여되는 뱃지", "14일 연속 접속 시 부여되는 뱃지", "21일 연속 접속 시 부여되는 뱃지",
                "28일 연속 접속 시 부여되는 뱃지", "35일 연속 접속 시 부여되는 뱃지", "42일 연속 접속 시 부여되는 뱃지"
        };

        String[] imgAddresses = {
                "assets/badge/badge1.png", "assets/badge/badge2.png", "assets/badge/badge3.png",
                "assets/badge/badge4.png", "assets/badge/badge5.png", "assets/badge/badge6.png"
        };

        for (int i = 0; i < badgeNames.length; i++) {
            String insertQuery = "INSERT INTO badge (badge_name, badge_description, img_address) VALUES " +
                    "('" + badgeNames[i] + "', '" + badgeDescriptions[i] + "', '" + imgAddresses[i] + "');";
            entityManager.createNativeQuery(insertQuery).executeUpdate();
        }
    }
}
