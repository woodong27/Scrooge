package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.ArticleComment;
import com.scrooge.scrooge.dto.communityDto.ArticleCommentDto;
import com.scrooge.scrooge.repository.UserRepository;
import com.scrooge.scrooge.repository.community.ArticleCommentRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityCommentService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;


    @Transactional
    public void createCommunityComment(ArticleCommentDto articleCommentDto) {
        ArticleComment articleComment = new ArticleComment();

        articleComment.setContent(articleCommentDto.getContent());

        // 연결
        articleComment.setUser(userRepository.findById(articleCommentDto.getUserId()).orElse(null));
        articleComment.setArticle(articleRepository.findById(articleCommentDto.getArticleId()).orElse(null));

        articleCommentRepository.save(articleComment);
    }
}
