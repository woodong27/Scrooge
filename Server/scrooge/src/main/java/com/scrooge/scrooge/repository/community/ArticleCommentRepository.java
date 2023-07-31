package com.scrooge.scrooge.repository.community;

import com.scrooge.scrooge.domain.community.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
