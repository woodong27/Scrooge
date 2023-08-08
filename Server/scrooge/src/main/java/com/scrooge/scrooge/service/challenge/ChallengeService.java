package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.config.FileUploadProperties;
import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import com.scrooge.scrooge.dto.challengeDto.ChallengeExampleImageDto;
import com.scrooge.scrooge.dto.challengeDto.ChallengeReqDto;
import com.scrooge.scrooge.dto.challengeDto.ChallengeRespDto;
import com.scrooge.scrooge.dto.challengeDto.ChallengeStartRespDto;
import com.scrooge.scrooge.repository.member.MemberRepository;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void createChallenge(ChallengeReqDto challengeReqDto/*, List<MultipartFile> images*/) {
        Challenge challenge = new Challenge();

        // 챌린지 정보 저장하기
        challenge.setChallengeMaster(memberRepository.findById(challengeReqDto.getChallengeMasterId()).orElse(null));
        challenge.setTitle(challengeReqDto.getTitle());
        challenge.setPeriod(challengeReqDto.getPeriod());
        challenge.setCategory(challengeReqDto.getCategory());
        challenge.setMinParticipants(challengeReqDto.getMinParticipants());
        challenge.setMaxParticipants(20);
        challenge.setStatus(1);
        challenge.setAuthMethod(challengeReqDto.getAuthMethod());
        challenge.setDescription(challengeReqDto.getDescription());

        // Period에 따라 총 인증 횟수 정하기
        Integer totalAuthCount = 0;
        switch (challengeReqDto.getPeriod()) {
            case "1주" :
                totalAuthCount = 7;
                break;
            case "2주" :
                totalAuthCount = 14;
                break;
            case "3주" :
                totalAuthCount = 21;
                break;
            case "한달":
                totalAuthCount = 28;
                break;
        }

        challenge.setTotalAuthCount(totalAuthCount);

        challengeRepository.save(challenge);

        // challengeMaster를 challengeParticipant에 추가하기
        ChallengeParticipant challengeParticipant = new ChallengeParticipant();
        challengeParticipant.setTeam(0);
        challengeParticipant.setMember(memberRepository.findById(challengeReqDto.getChallengeMasterId()).orElse(null));
        challengeParticipant.setChallenge(challenge);
        challengeParticipantRepository.save(challengeParticipant);

        // ChallengeExampleImage에 이미지 5장 저장
//        saveChallengeExampleImages(challenge, images);
    }

    // 챌린지에 해당하는 성공 예시 이미지 ChallengeExampleImage에 저장하기
    private void saveChallengeExampleImages(Challenge challenge, List<MultipartFile> images) {

        // 업로드할 위치 설정
        String uploadLocation = fileUploadProperties.getUploadLocation() + "/challenge/examples/" + challenge.getId();

        System.out.println(images.size());

        for(MultipartFile image : images) {
            // 업로드된 사진의 파일명을 랜덤 UUID로 생성
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = null;

            try {
                // 업로드할 위치에 폴더가 없으면 생성
                File uploadDir = new File(uploadLocation);
                if(!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

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
        return getChallengeRespDtos(challenges);
    }

    // 카테고리 별 챌린지 전체를 조회하는 API
    public List<ChallengeRespDto> getChallengesbyCategory(Integer categoryId) {
        String category = "";
        switch (categoryId){
            case 1:
                category = "식비";
                break;
            case 2:
                category = "교통비";
                break;
            case 3:
                category = "쇼핑";
                break;
            case 4:
                category = "기타";
                break;
        }

        List<Challenge> challenges = challengeRepository.findAllByCategory(category);
        return getChallengeRespDtos(challenges);
    }

    // 마이 챌린지 조회하는 API
    public List<ChallengeRespDto> getMyChallenges(Long memberId, Integer statusId) {
        List<Challenge> challenges = new ArrayList<>();
        List<ChallengeParticipant> challengeParticipantList = challengeParticipantRepository.findAllByMemberId(memberId);

        // status가 같은 애만 추가해주기
        for(ChallengeParticipant challengeParticipant : challengeParticipantList) {
            if(statusId == challengeParticipant.getChallenge().getStatus()) {
                challenges.add(challengeParticipant.getChallenge());
            }
        }

        return getChallengeRespDtos(challenges);
    }


    private List<ChallengeRespDto> getChallengeRespDtos(List<Challenge> challenges) {
        return challenges.stream()
                .map(challenge -> {
                    ChallengeRespDto challengeRespDto = new ChallengeRespDto();
                    challengeRespDto.setId(challenge.getId());
                    challengeRespDto.setCategory(challenge.getCategory());
                    challengeRespDto.setTitle(challenge.getTitle());
                    challengeRespDto.setCurrentParticipants(challenge.getChallengeParticipantList().size());
                    challengeRespDto.setMinParticipants(challenge.getMinParticipants());
                    // challengeRespDto.setMainImgAddress(challenge.getChallengeExampleImageList().get(0).getImgAddress());
                    challengeRespDto.setPeriod(challenge.getPeriod());

                    return challengeRespDto;
                })
                .collect(Collectors.toList());
    }

    // 챌린지 상세 조회 API
    public ChallengeRespDto getChallenge(Long challengeId) throws IllegalAccessException {
        Optional<Challenge> challenge = challengeRepository.findById(challengeId);

        if(challenge.isPresent()) {
            ChallengeRespDto challengeRespDto = new ChallengeRespDto();
            challengeRespDto.setId(challenge.get().getId());
            // challengeRespDto.setMainImgAddress(challenge.get().getChallengeExampleImageList().get(0).getImgAddress());
            challengeRespDto.setCategory(challenge.get().getCategory());
            challengeRespDto.setTitle(challenge.get().getTitle());
            challengeRespDto.setPeriod(challenge.get().getPeriod());
            challengeRespDto.setCurrentParticipants(challenge.get().getChallengeParticipantList().size());
            challengeRespDto.setMinParticipants(challenge.get().getMinParticipants());
            challengeRespDto.setAuthMethod(challenge.get().getAuthMethod());
            challengeRespDto.setDescription(challenge.get().getDescription());

            List<ChallengeExampleImage> challengeExampleImageList = challengeExampleImageRepository.findByChallengeId(challengeId);
            challengeRespDto.setChallengeExampleImageList(challengeExampleImageList.stream()
                    .map(ChallengeExampleImageDto::new)
                    .collect(Collectors.toList()));

            return challengeRespDto;
        } else {
            throw new IllegalAccessException("Challenge not found with ID: " + challengeId);
        }
    }

    // 챌린지 참여 API
    public void participateInChallenge(Long challengeId, Long memberId) {
        ChallengeParticipant challengeParticipant = new ChallengeParticipant();

        // 해당 challengeId의 challenge의 팀0 인원과 팀1의 인원 비교
        Integer teamZeroMemberCnt = challengeParticipantRepository.countTeamZeroByChallengeId(challengeId);
        Integer teamOneMemberCnt = challengeParticipantRepository.countTeamOneByChallengeId(challengeId);

        Integer team = 0; // 팀 0으로 일단 배정
        if(teamZeroMemberCnt > teamOneMemberCnt) { // 팀 1의 인원이 더 작을 경우 team1에 사용자 넣어주기
            team = 1;
        }

        challengeParticipant.setTeam(team);
        challengeParticipant.setChallenge(challengeRepository.findById(challengeId).orElse(null));
        challengeParticipant.setMember(memberRepository.findById(memberId).orElse(null));

        // challengeParticipant에 참여자 정보 추가하기
        challengeParticipantRepository.save(challengeParticipant);
    }

    // 챌린지 시작 API
    public ChallengeStartRespDto startChallenge(Long challengeId) {
        // 챌린지 시작 응답 DTO 생성
        ChallengeStartRespDto challengeStartRespDto = new ChallengeStartRespDto();

        /* 챌린지 시작 조건이 맞는지 먼저 확인 */

        // 1. challengeId에 해당하는 챌린지 가져오기
        Optional<Challenge> challenge = challengeRepository.findById(challengeId);
        // 2. challenge의 현재 인원 가져오기
        Integer currentParticipants = challenge.get().getChallengeParticipantList().size();

        // 3. 시작 조건 확인하기
        // 3-1. challenge의 현재 인원이 짝수 인지 확인하기
        if(currentParticipants % 2 == 1) {
            challengeStartRespDto.setStatus("Fail");
            challengeStartRespDto.setMessage("현재 인원이 홀수라서 챌린지를 시작할 수 없습니다.");
            return challengeStartRespDto;
        }
        // 3-2. challenge의 현재 인원이 최소 인원 이상인지 확인하기
        else if(currentParticipants < challenge.get().getMinParticipants()){
            challengeStartRespDto.setStatus("Fail");
            challengeStartRespDto.setMessage("현재 인원이 최소 인원 미만이라 챌린지를 시작할 수 없습니다.");
            return challengeStartRespDto;
        }
        else {
            /* 시작할 수 있다!! */

            /* challenge 테이블에서 수정되는 내용 바꾸기 */
            // 1. status를 1(시작 전) 에서 2(진행 중)으로 변경하기
            challenge.get().setStatus(2);
            // 2. startDate를 오늘 날짜로 정하기
            challenge.get().setStartDate(LocalDateTime.now());
            // 3. endDate를 startDate에서 period(totalAuthCount) 만큼 더한 날짜로 저장
            challenge.get().setEndDate(challenge.get().getStartDate().plusDays(challenge.get().getTotalAuthCount()).with(LocalTime.of(23, 59, 59)));
            challengeRepository.save(challenge.get());

            // 시작 성공 응답 보내주기
            challengeStartRespDto.setStatus("Success");
            challengeStartRespDto.setMessage("챌린지 시작에 성공하였습니다.");
            return challengeStartRespDto;
        }
    }
}
