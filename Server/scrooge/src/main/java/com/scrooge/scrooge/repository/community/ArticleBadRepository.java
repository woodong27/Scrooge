package com.scrooge.scrooge.repository.community;

import com.scrooge.scrooge.domain.community.ArticleBad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleBadRepository extends JpaRepository<ArticleBad, Long> {

    Optional<ArticleBad> findByArticleIdAndMemberId(Long articleId, Long memberId);

    boolean existsByArticleIdAndMemberId(Long articleId, Long memberId);

    void deleteByArticleIdAndMemberId(Long articleId, Long memberId);

    @Query("SELECT COUNT(ab) from ArticleBad ab where ab.article.id = ?1")
    Integer countByArticleId(Long articleId);
}
