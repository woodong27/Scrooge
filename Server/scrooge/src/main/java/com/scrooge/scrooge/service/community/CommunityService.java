package com.scrooge.scrooge.service.community;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.scrooge.scrooge.config.FileUploadProperties;
import com.scrooge.scrooge.domain.community.Article;
import com.scrooge.scrooge.domain.community.ArticleBad;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.communityDto.ArticleDto;
import com.scrooge.scrooge.dto.communityDto.ArticleReviewCountDto;
import com.scrooge.scrooge.dto.member.ArticleMemberDto;
import com.scrooge.scrooge.repository.community.ArticleBadRepository;
import com.scrooge.scrooge.repository.community.ArticleGoodRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.member.MemberSelectedQuestRepository;
import com.scrooge.scrooge.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final FileUploadProperties fileUploadProperties;
    private final MemberSelectedQuestRepository memberSelectedQuestRepository;
    private final QuestService questService;
    private final ArticleGoodRepository articleGoodRepository;
    private final ArticleBadRepository articleBadRepository;

    private final Storage storage;
    private final String bucketName = "scroogestorage";
    private final String GCP_ADDRESS = "https://storage.googleapis.com/";


    // 커뮤니티 글을 등록하는 메서드
    @Transactional
    public ArticleDto createArticle(String content, MultipartFile img, Long memberId) throws IOException {

        System.out.println(content);
        System.out.println(memberId);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        Article article = new Article();
        article.setContent(content);
        article.setMember(member);
        article.setCreatedAt(LocalDateTime.now());

        // 게시글 작성 퀘스트
        if (memberSelectedQuestRepository.existsByMemberIdAndQuestId(memberId, 4L)) {
            questService.completeQuest(4L, memberId);
        }

        // 이미지 파일 등록 구현

        String uuid = UUID.randomUUID().toString();
        String ext = img.getContentType();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uuid)
                        .setContentType(ext)
                        .build();

        storage.create(blobInfo, img.getInputStream());

        String imgAddress = GCP_ADDRESS + bucketName + "/" + uuid;
        article.setImgAdress(imgAddress);

        articleRepository.save(article); // DB에 article 저장

        return new ArticleDto(article);
    }

    // 커뮤니티 전체 글을 조회하는 API
    public List<ArticleDto> getAllCommunityArticles() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<Article> articles = articleRepository.findAll(sort);
        return articles.stream()
                .map(ArticleDto::new)
                .collect(Collectors.toList());
    }

    // 커뮤니티 글을 상세 조회하는 API
    public ArticleDto getCommunityArticle(Long articleId) throws NotFoundException {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        return new ArticleDto(article);
    }

    // 커뮤니티 글을 수정하는 API
    public ArticleDto updateCommunityArticle(Long articleId, String content) throws NotFoundException {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("해당 게시글을 찾을 수 없습니다."));

        article.setContent(content);
        articleRepository.save(article);

        return new ArticleDto(article);
    }

    // 커뮤니티 글을 삭제하는 API
    public void deleteCommunityArticle(Long articleId) throws NotFoundException {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("해당 게시글을 찾을 수 없습니다."));

        articleRepository.delete(article);
    }

    public ArticleReviewCountDto countArticleReview(Long articleId) {
        Integer goodCount = articleGoodRepository.countByArticleId(articleId);
        Integer badCount = articleBadRepository.countByArticleId(articleId);

        return new ArticleReviewCountDto(goodCount, badCount);
    }

}
