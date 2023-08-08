package com.scrooge.scrooge.repository.community;

import com.scrooge.scrooge.domain.community.ArticleBad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleBadRepository extends JpaRepository<ArticleBad, Long> {

    Optional<ArticleBad> findByArticleIdAndMemberId(Long articleId, Long memberId);

    boolean existsByArticleIdAndMemberId(Long articleId, Long memberId);

    void deleteByArticleIdAndMemberId(Long articleId, Long memberId);

    Integer countByArticleId(Long articleId);
}
