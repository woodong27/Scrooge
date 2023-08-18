package com.scrooge.scrooge.repository.community;

import com.scrooge.scrooge.domain.community.ArticleGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleGoodRepository extends JpaRepository<ArticleGood, Long> {
    Optional<ArticleGood> findByArticleIdAndMemberId(Long articleId, Long memberId);


    void deleteByArticleIdAndMemberId(Long articleId, Long memberId);

    boolean existsByArticleIdAndMemberId(Long articleId, Long memberId);

    Integer countByArticleId(Long articleId);
}
