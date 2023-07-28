package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.Article;
import com.scrooge.scrooge.domain.community.ArticleBad;
import com.scrooge.scrooge.domain.community.ArticleGood;
import com.scrooge.scrooge.dto.communityDto.ArticleBadDto;
import com.scrooge.scrooge.dto.communityDto.ArticleDto;
import com.scrooge.scrooge.dto.communityDto.ArticleGoodDto;
import com.scrooge.scrooge.repository.UserRepository;
import com.scrooge.scrooge.repository.community.ArticleBadRepository;
import com.scrooge.scrooge.repository.community.ArticleGoodRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final UserRepository userRepository;

    // 커뮤니티 글을 등록하는 메서드
    @Transactional
    public void createArticle(ArticleDto articleDto, MultipartFile img) {

        Article article = new Article();

        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setUser(userRepository.findById(articleDto.getUserId()).orElse(null));

        // 이미지 파일 등록 구현


    }
}
