package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.config.FileUploadProperties;
import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeAuth;
import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import com.scrooge.scrooge.dto.challengeDto.ChallengeStartRespDto;
import com.scrooge.scrooge.dto.challengeDto.MyChallengeRespDto;
import com.scrooge.scrooge.repository.challenge.ChallengeAuthRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeParticipantRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StartChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeParticipantRepository challengeParticipantRepository;
    private final ChallengeAuthRepository challengeAuthRepository;

    private final FileUploadProperties fileUploadProperties;

    // 사용자가 참여한 시작된 챌린지에 대한 정보 조회
    public MyChallengeRespDto getMyStartedChallenge(Long challengeId) throws IllegalAccessException {

        Optional<Challenge> challenge = challengeRepository.findById(challengeId);

        if(challenge.isPresent()) {
            MyChallengeRespDto myChallengeRespDto = new MyChallengeRespDto();
            myChallengeRespDto.setId(challenge.get().getId());
            myChallengeRespDto.setMainImgAddress(challenge.get().getChallengeExampleImageList().get(0).getImgAddress());
            myChallengeRespDto.setStartDate(challenge.get().getStartDate());
            myChallengeRespDto.setEndDate(challenge.get().getEndDate());
            myChallengeRespDto.setPeriod(challenge.get().getPeriod());

            /* 대전 현황 구현 위해 각 팀의 성공 횟수 가져오기 */
            Integer teamZeroSuccessCount, teamOneSuccessCount;
            teamZeroSuccessCount = challengeAuthRepository.countZeroSuccessCount(challengeId);
            teamOneSuccessCount = challengeAuthRepository.countOneSuccessCount(challengeId);

            myChallengeRespDto.setTeamZeroSuccessCount(teamZeroSuccessCount);
            myChallengeRespDto.setTeamOneSuccessCount(teamOneSuccessCount);

            return myChallengeRespDto;
        }else {
            throw new IllegalAccessException("Challenge not found with Id : " + challengeId);
        }

    }

    // 사용자 인증 등록 API
    public ChallengeStartRespDto createMyChallengeAuth(Long challengeId, Long memberId, MultipartFile img) {
        ChallengeAuth challengeAuth = new ChallengeAuth();

        // 일단 성공으로 가정 (추후에 AI로 검증 필요)
        challengeAuth.setIsSuccess(true);
        //오늘 날짜로 정하기
        challengeAuth.setCreatedAt(LocalDateTime.now());


        // challengeParticipantId 찾기
        ChallengeParticipant challengeParticipant = challengeParticipantRepository.findByMemberIdAndChallengeId(memberId, challengeId);
        challengeAuth.setChallengeParticipant(challengeParticipant);

        /* 이미지 파일 등록 구현 */
        // 업로드할 위치 설정
        String uploadLocation = fileUploadProperties.getUploadLocation() + "/challenge/memberImg/" + challengeId;

        // 업로드할 사진의 파일명을 랜덤 UUID로 생성
        String fileName = UUID.randomUUID().toString() + "_" + img.getOriginalFilename();
        Path filePath = null;

        try {
            // 업로드할 위치에 폴더가 없으면 생성
            File uploadDir = new File(uploadLocation);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // 업로드할 위치에 파일 저장
            byte[] bytes = img.getBytes();
            filePath = Paths.get(uploadLocation + File.separator + fileName);
            Files.write(filePath, bytes);

            // 이미지 주소 저장하기
            challengeAuth.setImgAddress(filePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        challengeAuthRepository.save(challengeAuth);

        ChallengeStartRespDto challengeStartRespDto = new ChallengeStartRespDto();
        challengeStartRespDto.setStatus("Success");
        challengeStartRespDto.setMessage("챌린지 인증에 성공했습니다.");
        return challengeStartRespDto;
    }



}
