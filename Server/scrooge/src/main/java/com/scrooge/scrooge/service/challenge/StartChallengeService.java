package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.config.FileUploadProperties;
import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeAuth;
import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import com.scrooge.scrooge.dto.challengeDto.ChallengeAuthDto;
import com.scrooge.scrooge.dto.challengeDto.ChallengeStartRespDto;
import com.scrooge.scrooge.dto.challengeDto.MyChallengeMyAuthDto;
import com.scrooge.scrooge.dto.challengeDto.MyChallengeRespDto;
import com.scrooge.scrooge.repository.challenge.ChallengeAuthRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeParticipantRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

        // 인증 검사
        //실패
        // setisSuccess = false;
        // 저장 안하고

        // 검사 성공
        challengeAuth.setIsSuccess(true);
        challengeAuthRepository.save(challengeAuth);

        ChallengeStartRespDto challengeStartRespDto = new ChallengeStartRespDto();
        challengeStartRespDto.setStatus("Success");
        challengeStartRespDto.setMessage("챌린지 인증에 성공했습니다.");
        return challengeStartRespDto;
    }


    // 나의 인증 현황 조회하는 API
    public MyChallengeMyAuthDto getMyChallengeMyAuth(Long challengeId, Long memberId) {

        MyChallengeMyAuthDto myChallengeMyAuthDto = new MyChallengeMyAuthDto();

        /* 달성률 계산하기 */
        // 1. challengeId와 memberId에 해당하는 challengeParticipant를 가져온다.
        ChallengeParticipant challengeParticipant = challengeParticipantRepository.findByMemberIdAndChallengeId(memberId, challengeId);
        // 2. 현재 challengeParticipant가 성공한 횟수를 가져온다.
        Integer curSuccessCount = challengeAuthRepository.countSuccessCountByChallengeParticipantId(challengeParticipant.getId());
        // 3. challengeId에 해당하는 challenge의 총 인증해야하는 횟수를 가져온다.
        Integer totalAuthCount = challengeRepository.findById(challengeId).get().getTotalAuthCount();
        //4. 현재 달성률을 계산한다.
        double currentCompletionRate = (double)curSuccessCount / totalAuthCount * 100;
        myChallengeMyAuthDto.setCurrentCompletionRate((int)currentCompletionRate);

        /* challengeAuth 목록 리턴하기 */
        // 1. challengeParticipant ID에 해당하는 challengeAuthList를 가져오기
        List<ChallengeAuth> challengeAuthList = challengeAuthRepository.findAllByChallengeParticipantId(challengeParticipant.getId());
        // 2. 직렬화,,
        myChallengeMyAuthDto.setChallengeAuthList(challengeAuthList.stream()
                .map(ChallengeAuthDto::new)
                .collect(Collectors.toList()));

        return myChallengeMyAuthDto;
    }

    // endDate의 시간이 지나면 자동으로 status 3으로 변경, 즉 상태가 종료됨으로 변경된다.
    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void checkEndDateAndUpdateChallengeStatus() {
        // 모든 진행 중인 Challenge를 challenges 리스트에 저장한다.
        List<Challenge> challenges = challengeRepository.findAllByStatus2();

        // 현재 시간을 받아온다.
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 진행중인 리스트의 challenge를 하나씩 접근하여 현재 시간이 endDate를 지났을 경우 status를 3, 즉 종료로 변경해준다.
        for(Challenge challenge : challenges) {
            if(currentDateTime.isAfter(challenge.getEndDate())) {
                challenge.setStatus(3);
                challengeRepository.save(challenge);
            }
        }
    }

    public double imageAnalysis(Long challengeId, Long authImageId) {
        return 0.1;
    }

}
