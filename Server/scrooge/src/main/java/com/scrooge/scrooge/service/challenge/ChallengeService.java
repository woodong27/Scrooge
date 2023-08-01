package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.config.FileUploadProperties;
import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import com.scrooge.scrooge.dto.challengeDto.ChallengeReqDto;
import com.scrooge.scrooge.dto.challengeDto.ChallengeRespDto;
import com.scrooge.scrooge.repository.MemberRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeExampleImageRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeParticipantRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeExampleImageRepository challengeExampleImageRepository;
    private final ChallengeParticipantRepository challengeParticipantRepository;

    private final FileUploadProperties fileUploadProperties;

    // 챌린지 생성 API
    @Transactional
    public void createChallenge(ChallengeReqDto challengeReqDto, List<MultipartFile> images) {
        Challenge challenge = new Challenge();

        // 챌린지 정보 저장하기
        challenge.setChallengeMaster(memberRepository.findById(challengeReqDto.getChallengeMasterId()).orElse(null));
        challenge.setTitle(challengeReqDto.getTitle());
        challenge.setPeriod(challengeReqDto.getPeriod());
        challenge.setCategory(challengeReqDto.getCategory());
        challenge.setMinParticipants(challengeReqDto.getMinParticipants());
        challenge.setMaxParticipants(20);
        challenge.setStatus(0);
        challenge.setAuthMethod(challengeReqDto.getAuthMethod());
        challenge.setDescription(challengeReqDto.getDescription());

        challengeRepository.save(challenge);

        // challengeMaster를 challengeParticipant에 추가하기
        ChallengeParticipant challengeParticipant = new ChallengeParticipant();
        challengeParticipant.setTeam(0);
        challengeParticipant.setMember(memberRepository.findById(challengeReqDto.getChallengeMasterId()).orElse(null));
        challengeParticipant.setChallenge(challenge);
        challengeParticipantRepository.save(challengeParticipant);

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

    // 챌린지 전체를 조회하는 API
    public List<ChallengeRespDto> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        return challenges.stream()
                .map(challenge -> {
                    ChallengeRespDto challengeRespDto = new ChallengeRespDto();
                    challengeRespDto.setId(challenge.getId());
                    challengeRespDto.setCategory(challenge.getCategory());
                    challengeRespDto.setTitle(challenge.getTitle());
                    challengeRespDto.setCurrentParticipants(challenge.getChallengeParticipantList().size());
                    challengeRespDto.setMinParticipants(challenge.getMinParticipants());
                    challengeRespDto.setMainImgAddress(challenge.getChallengeExampleImageList().get(0).getImgAddress());

                    return challengeRespDto;
                })
                .collect(Collectors.toList());
    }
}
