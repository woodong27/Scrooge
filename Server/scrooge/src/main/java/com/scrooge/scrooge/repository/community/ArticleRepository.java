package com.scrooge.scrooge.repository.community;

import com.scrooge.scrooge.domain.community.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
