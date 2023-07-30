package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.config.FileUploadProperties;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    private final FileUploadProperties fileUploadProperties;

    // 커뮤니티 글을 등록하는 메서드
    @Transactional
    public void createArticle(ArticleDto articleDto, MultipartFile img) {

        Article article = new Article();

        article.setContent(articleDto.getContent());
        article.setUser(userRepository.findById(articleDto.getUserId()).orElse(null));

        // 이미지 파일 등록 구현

        // 업로드할 위치 설정
        String uploadLocation = fileUploadProperties.getUploadLocation();

        // 업로드된 사진의 파일명을 랜덤 UUID로 생성
        String fileName = UUID.randomUUID().toString() + "_" + img.getOriginalFilename();

        Path filePath = null;

        try {
            // 업로드할 위치에 파일 저장
            byte[] bytes = img.getBytes();
            filePath = Paths.get(uploadLocation + File.separator + fileName);
            Files.write(filePath, bytes);
        } catch (IOException e) { // 파일 저장 중 예외 처리
            e.printStackTrace();
        }

        article.setImgAdress(filePath.toString());

        articleRepository.save(article); // DB에 article 저장

    }

    // 커뮤니티 전체 글을 조회하는 API
    public List<ArticleDto> getAllCommunityArticles() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<Article> articles = articleRepository.findAll(sort);
        return articles.stream()
                .map(article -> {
                    ArticleDto articleDto = new ArticleDto();
                    articleDto.setId(article.getId());
                    articleDto.setContent(article.getContent());
                    articleDto.setImgAdress(article.getImgAdress());
                    articleDto.setCreatedAt(article.getCreatedAt()); //필요X?

                    // user 관련 정보
                    articleDto.setUserId(article.getUser().getId()); //필요X?
                    articleDto.setNickname(article.getUser().getNickname());
                    articleDto.setAvatarImgAddress(article.getUser().getMainAvatar().getImgAddress());

                    return articleDto;
                })
                .collect(Collectors.toList());
    }
}
