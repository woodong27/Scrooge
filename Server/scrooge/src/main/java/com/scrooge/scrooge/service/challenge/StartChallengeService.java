package com.scrooge.scrooge.service.challenge;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.scrooge.scrooge.config.FileUploadProperties;
import com.scrooge.scrooge.controller.challenge.ImageCompareController;
import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeAuth;
import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import com.scrooge.scrooge.dto.challengeDto.*;
import com.scrooge.scrooge.repository.challenge.ChallengeAuthRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeExampleImageRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeParticipantRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
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
    private final ChallengeExampleImageRepository challengeExampleImageRepository;

    private final FileUploadProperties fileUploadProperties;
    private final ImageCompareController imageCompareController;

    private final Storage storage;
    private final String bucketName = "scroogestorage";
    private final String GCP_ADDRESS = "https://storage.googleapis.com/";

    // 사용자가 참여한 시작된 챌린지에 대한 정보 조회
    public MyChallengeRespDto getMyStartedChallenge(Long challengeId) throws Exception {

        Optional<Challenge> challenge = challengeRepository.findById(challengeId);

        if(challenge.isPresent()) {
            if(challenge.get().getStatus() == 2) {
                MyChallengeRespDto myChallengeRespDto = new MyChallengeRespDto();
                myChallengeRespDto.setId(challenge.get().getId());
                // myChallengeRespDto.setMainImgAddress(challenge.get().getChallengeExampleImageList().get(0).getImgAddress());
                myChallengeRespDto.setStartDate(challenge.get().getStartDate());
                myChallengeRespDto.setEndDate(challenge.get().getEndDate());
                myChallengeRespDto.setPeriod(challenge.get().getPeriod());
                myChallengeRespDto.setTitle(challenge.get().getTitle());

                /* 대전 현황 구현 위해 각 팀의 성공 횟수 가져오기 */
                Integer teamZeroSuccessCount, teamOneSuccessCount;
                teamZeroSuccessCount = challengeAuthRepository.countZeroSuccessCount(challengeId);
                teamOneSuccessCount = challengeAuthRepository.countOneSuccessCount(challengeId);

                myChallengeRespDto.setTeamZeroSuccessCount(teamZeroSuccessCount);
                myChallengeRespDto.setTeamOneSuccessCount(teamOneSuccessCount);

                return myChallengeRespDto;
            }
            else{
                throw new Exception("해당 챌린지는 진행 중인 챌린지가 아닙니다.");
            }


        }else {
            throw new IllegalAccessException("Challenge not found with Id : " + challengeId);
        }

    }

    // 사용자 인증 등록 API
    public ChallengeStartRespDto createMyChallengeAuth(Long challengeId, Long memberId, MultipartFile img) throws IOException {
        ChallengeAuth challengeAuth = new ChallengeAuth();

        //오늘 날짜로 정하기
        challengeAuth.setCreatedAt(LocalDateTime.now());

        String authImageAddress = uploadAuthImage(img);
        challengeAuth.setImgAddress(authImageAddress);

        ChallengeParticipant challengeParticipant = challengeParticipantRepository.findByMemberIdAndChallengeId(memberId, challengeId);
        challengeAuth.setChallengeParticipant(challengeParticipant);
        // stackoverflow 에러 발생

        List<ChallengeExampleImage> challengeExampleImageList = challengeExampleImageRepository.findByChallengeId(challengeId);

        for (ChallengeExampleImage challengeExampleImage : challengeExampleImageList) {
            if (imageCompareController.sendImages(new ImagePaths(challengeExampleImage.getImgAddress(), authImageAddress)).getBody() >= 0.7) {
                challengeAuth.setIsSuccess(true);
                challengeAuthRepository.save(challengeAuth);

                ChallengeStartRespDto challengeStartRespDto = new ChallengeStartRespDto();
                challengeStartRespDto.setStatus("Successed");
                challengeStartRespDto.setMessage("챌린지 인증에 성공했습니다.");
                return challengeStartRespDto;
            }
        }

        // 5개 사진에 대해서 전부 유사도가 0.8이상이 되지 못했음 -> 실패
        challengeAuth.setIsSuccess(false);
        challengeAuthRepository.save(challengeAuth);

        ChallengeStartRespDto challengeStartRespDto = new ChallengeStartRespDto();
        challengeStartRespDto.setStatus("Failed");
        challengeStartRespDto.setMessage("챌린지 인증에 실패했습니다.");
        return challengeStartRespDto;
    }



    // 나의 인증 현황 조회하는 API
    public MyChallengeMyAuthDto getMyChallengeMyAuth(Long challengeId, Long memberId) {

        MyChallengeMyAuthDto myChallengeMyAuthDto = new MyChallengeMyAuthDto();

        /* 달성률 계산하기 */
        // 1. challengeId와 memberId에 해당하는 challengeParticipant를 가져온다.
        ChallengeParticipant challengeParticipant = challengeParticipantRepository.findByMemberIdAndChallengeId(memberId, challengeId);

        // 2. 현재 challengeParticipant가 성공한 횟수를 가져온다.
        Integer currentSuccessCount = challengeAuthRepository.countByChallengeParticipantIdAndIsSuccess(challengeParticipant.getId(), true);

        // 3. challengeId에 해당하는 challenge의 총 인증해야하는 횟수를 가져온다.
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new NotFoundException("해당 챌린지를 찾을 수 없습니다."));
        Integer totalAuthCount = challenge.getTotalAuthCount();

        //4. 현재 달성률을 계산한다.
        double currentCompletionRate = (double)currentSuccessCount / totalAuthCount * 100;
        myChallengeMyAuthDto.setCurrentCompletionRate((int)currentCompletionRate);

        /* challengeAuth 목록 리턴하기 */
        // 1. challengeParticipant ID에 해당하는 challengeAuthList를 가져오기
        List<ChallengeAuth> challengeAuthList = challengeAuthRepository.findByChallengeParticipantId(challengeParticipant.getId());
        // 2. 직렬화,,
        myChallengeMyAuthDto.setChallengeAuthList(challengeAuthList.stream()
                .map(ChallengeAuthDto::new)
                .collect(Collectors.toList()));

        return myChallengeMyAuthDto;
    }

    // 우리팀의 챌린지 인증 현황 가져오기
    public List<TeamMemberAuths> getTeamAuths(Long challengeId, Long memberId) {
        ChallengeParticipant challengeParticipant = challengeParticipantRepository.findByMemberIdAndChallengeId(memberId, challengeId);
        Integer team = challengeParticipant.getTeam();

        return challengeParticipantRepository.findByChallengeIdAndTeam(challengeId, team).stream()
                .map(TeamMemberAuths::new)
                .collect(Collectors.toList());
    }


    // endDate의 시간이 지나면 자동으로 status 3으로 변경, 즉 상태가 종료됨으로 변경된다.
    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void checkEndDateAndUpdateChallengeStatus() {
        // 모든 진행 중인 Challenge를 challenges 리스트에 저장한다.
        List<Challenge> challenges = challengeRepository.findAllByStatus(2);

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

    // Google Cloud platform에 이미지 업로드
    public String uploadAuthImage(MultipartFile img) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String ext = img.getContentType();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uuid)
                .setContentType(ext)
                .build();

        storage.create(blobInfo, img.getInputStream());

        String imgAddress = GCP_ADDRESS + bucketName + "/" + uuid;
        return imgAddress;
    }

}
