package com.scrooge.scrooge.domain.community;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.scrooge.scrooge.domain.User;

@Entity
@Table(name = "article_bad")
@Data
@NoArgsConstructor
public class ArticleBad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* 연결 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

}
