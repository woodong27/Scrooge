package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.config.FileUploadProperties;
import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import com.scrooge.scrooge.dto.challengeDto.ChallengeDto;
import com.scrooge.scrooge.repository.MemberRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeExampleImageRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeRepository;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeExampleImageRepository challengeExampleImageRepository;

    private final FileUploadProperties fileUploadProperties;

    // 챌린지 생성 API
    @Transactional
    public void createChallenge(ChallengeDto challengeDto, List<MultipartFile> images) {
        Challenge challenge = new Challenge();

        // 챌린지 정보 저장하기
        challenge.setChallengeMaster(memberRepository.findById(challengeDto.getChallengeMasterId()).orElse(null));
        challenge.setTitle(challengeDto.getTitle());
        challenge.setPeriod(challengeDto.getPeriod());
        challenge.setCategory(challengeDto.getCategory());
        challenge.setMinParticipants(challengeDto.getMinParticipants());
        challenge.setMaxParticipants(challengeDto.getMaxParticipants());
        challenge.setAuthMethod(challengeDto.getAuthMethod());
        challenge.setDescription(challengeDto.getDescription());

        challengeRepository.save(challenge);

        // ChallengeExampleImage에 이미지 5장 저장
        saveChallengeExampleImages(challenge, images);
    }

    // 챌린지에 해당하는 성공 예시 이미지 ChallengeExampleImage에 저장하기
    private void saveChallengeExampleImages(Challenge challenge, List<MultipartFile> images) {

        // 업로드할 위치 설정
        String uploadLocation = fileUploadProperties.getUploadLocation() + "/challenge/examples";

        System.out.println(images.size());

        for(MultipartFile image : images) {
            // 업로드된 사진의 파일명을 랜덤 UUID로 생성
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = null;

            try {
                // 업로드할 위치에 파일 저장
                byte[] bytes = image.getBytes();
                filePath = Paths.get(uploadLocation + File.separator + fileName);
                Files.write(filePath, bytes);

                // ChallengeExampleImage에 이미지를 저장한다.
                ChallengeExampleImage challengeExampleImage = new ChallengeExampleImage();
                challengeExampleImage.setChallenge(challenge);
                challengeExampleImage.setImgAddress(filePath.toString());

                challengeExampleImageRepository.save(challengeExampleImage); //DB에 이미지 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
