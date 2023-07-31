package com.scrooge.scrooge.repository.community;

import com.scrooge.scrooge.domain.community.ArticleComment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
    @Query("SELECT ac FROM ArticleComment ac WHERE ac.article.id = :articleId")
    List<ArticleComment> findByArticleId(Sort sort, Long articleId);
}
