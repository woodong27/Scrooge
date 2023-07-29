package com.scrooge.scrooge.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class QuestDataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        insertInitialData();
    }

    private void insertInitialData() {
        String[] titles = {
                "대중교통 이용하기", "파 키우기", "찬물로 샤워하기",
                "에어컨 대신 선풍기 틀기", "퀘스트22", "퀘스트23",
                "커피스틱 타먹기", "퀘스트3", "퀘스트333"
        };

        String[] descriptions = {
                "자가용이나 택시 대신 대중교통을 이용해 출근해봐요", "맛있는 파를 사지말고 직접 키워서 먹어봐요", "건강에 좋은 찬물샤워로 보일러비를 아껴봐요",
                "돈도 아끼고 환경도 지키고! 에어컨 대신 선풍기로 버텨봐요", "222222222", "2323232323",
                "비싼 카페 대신 맛있는 커피스틱을 이용해봐요", "33333333333333333", "333333333333333333333333333"
        };

        int[] maxCounts = {1, 1, 1, 2, 2, 2, 3, 3, 3};

        for (int i = 0; i < titles.length; i++) {
            String insertQuery = "INSERT INTO quest (title, description, max_count) VALUES " +
                    "('" + titles[i] + "', '" + descriptions[i] + "', " + maxCounts[i] + ");";
            entityManager.createNativeQuery(insertQuery).executeUpdate();
        }
    }
}
