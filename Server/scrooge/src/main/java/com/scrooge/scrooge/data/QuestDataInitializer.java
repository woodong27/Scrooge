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
                "일일 정산 4번", "일일 소비금액 2만원 이하(3일)", "댓글 5개 작성",
                "게시글 작성 2회", "챌린지 일일 참여 인증 5회", "게시글 평가 10회"
        };

        String[] descriptions = {
                "한 주 동안 일일 정산 4회 이상 하기", "한 주 동안 2만원 이하로 소비 3일 이상 하기",
                "아무 게시들이나 댓글 5회 이상 작성하기", "게시글 작성 2회 이상 하기",
                "본인이 참여한 챌린지에 인증 5회 이상 하기", "게시글 좋아요/싫어요 10회 이상 하기"
        };

        int[] maxCounts = {4, 3, 5, 2, 5, 10};

        for (int i = 0; i < titles.length; i++) {
            String insertQuery = "INSERT INTO quest (title, description, max_count) VALUES " +
                    "('" + titles[i] + "', '" + descriptions[i] + "', " + maxCounts[i] + ");";
            entityManager.createNativeQuery(insertQuery).executeUpdate();
        }
    }
}
