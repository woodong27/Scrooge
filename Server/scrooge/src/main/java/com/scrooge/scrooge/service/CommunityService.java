package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.community.ArticleBad;
import com.scrooge.scrooge.domain.community.ArticleGood;
import com.scrooge.scrooge.dto.communityDto.ArticleGoodDto;
import com.scrooge.scrooge.repository.UserRepository;
import com.scrooge.scrooge.repository.community.ArticleBadRepository;
import com.scrooge.scrooge.repository.community.ArticleGoodRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleGoodRepository articleGoodRepository;
    private final ArticleBadRepository articleBadRepository;

    /* 환호/야유 구현*/

    // Article 좋아요를 구현하는 메서드
    @Transactional
    public ArticleGood addCommunityGood(Long articleId, Long userId) {
        ArticleGood articleGood = new ArticleGood();

        articleGood.setUser(userRepository.findById(userId).orElse(null));
        articleGood.setArticle(articleRepository.findById(articleId).orElse(null));

        return articleGoodRepository.save(articleGood);
    }

    // Article 싫어요를 구현하는 메서드
    @Transactional
    public ArticleBad addCommunityBad(Long articleId, Long userId) {
        ArticleBad articleBad = new ArticleBad();

        articleBad.setUser(userRepository.findById(userId).orElse(null));
        articleBad.setArticle(articleRepository.findById(articleId).orElse(null));

        return articleBadRepository.save(articleBad);
    }
}
