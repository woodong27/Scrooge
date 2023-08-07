package com.scrooge.scrooge.repository.community;

import com.scrooge.scrooge.domain.community.ArticleGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleGoodRepository extends JpaRepository<ArticleGood, Long> {
//    @Query("select ag from ArticleGood ag where ag.article.id = :articleId and ag.member.id = :memberId")
    Optional<ArticleGood> findByArticleIdAndMemberId(Long articleId, Long memberId);

    @Query("SELECT COUNT(ag) FROM ArticleGood ag WHERE ag.article.id = ?1")
    Integer countByArticleId(Long articleId);

    void deleteByArticleIdAndMemberId(Long articleId, Long memberId);

    boolean existsByArticleIdAndMemberId(Long articleId, Long memberId);
}
