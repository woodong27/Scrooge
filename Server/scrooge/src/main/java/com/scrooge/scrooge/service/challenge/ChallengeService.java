package com.scrooge.scrooge.service.challenge;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.scrooge.scrooge.config.FileUploadProperties;
import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeChattingRoom;
import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.challengeDto.*;
import com.scrooge.scrooge.repository.challenge.ChallengeChattingRoomRepository;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeExampleImageRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeParticipantRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeExampleImageRepository challengeExampleImageRepository;
    private final ChallengeParticipantRepository challengeParticipantRepository;
    private final ChallengeChattingRoomRepository challengeChattingRoomRespository;

    private final FileUploadProperties fileUploadProperties;
//    private final UploadService uploadService;

    private final Storage storage;
    private static final String bucketName = "scroogestorage";
    private static final String GCP_ADDRESS = "https://storage.googleapis.com/";

    // 챌린지 생성 API
    @Transactional
    public ChallengeDetailDto createChallenge(ChallengeReqDto challengeReqDto, List<MultipartFile> images) throws IOException {
        Challenge challenge = new Challenge();

        // 챌린지 정보 저장하기
        challenge.setChallengeMaster(memberRepository.findById(challengeReqDto.getChallengeMasterId()).orElse(null));
        challenge.setTitle(challengeReqDto.getTitle());
        challenge.setPeriod(challengeReqDto.getPeriod());
        challenge.setCategory(challengeReqDto.getCategory());
        challenge.setMinParticipants(challengeReqDto.getMinParticipants());
        challenge.setMaxParticipants(20);
        challenge.setAuthMethod(challengeReqDto.getAuthMethod());
        challenge.setDescription(challengeReqDto.getDescription());
        challenge.setStatus(1);

        // Period에 따라 총 인증 횟수 정하기
        int totalAuthCount = 0;
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
        for (MultipartFile img : images) {
            uploadExampleImage(img, challenge);
        }

        List<ChallengeExampleImage> challengeExampleImageList = challengeExampleImageRepository.findByChallengeId(challenge.getId());
        return new ChallengeDetailDto(challenge, challengeExampleImageList);
    }

    public void uploadExampleImage(MultipartFile img, Challenge challenge) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String ext = img.getContentType();

        BlobInfo blobinfo = BlobInfo.newBuilder(bucketName, uuid)
                .setContentType(ext)
                .build();

        storage.create(blobinfo, img.getInputStream());

        String imgAddress = GCP_ADDRESS + bucketName + "/" + uuid;
        ChallengeExampleImage challengeExampleImage = new ChallengeExampleImage();
        challengeExampleImage.setChallenge(challenge);
        challengeExampleImage.setImgAddress(imgAddress);
        challengeExampleImageRepository.save(challengeExampleImage);
    }

    // 챌린지 전체를 조회하는 API
    public List<ChallengeResDto> getAllChallenges() {
        return challengeRepository.findAll().stream()
                .map(ChallengeResDto::new)
                .collect(Collectors.toList());
    }

    // 카테고리 별 챌린지 전체를 조회하는 API
    public List<ChallengeResDto> getChallengesbyCategory(Integer categoryId) {
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

        return challengeRepository.findAllByCategory(category).stream()
                .map(ChallengeResDto::new)
                .collect(Collectors.toList());
    }

    // 마이 챌린지 조회하는 API
    public List<ChallengeResDto> getMyChallenges(Long memberId, Integer statusId) {
        List<Challenge> challenges = new ArrayList<>();
        List<ChallengeParticipant> challengeParticipantList = challengeParticipantRepository.findAllByMemberId(memberId);

        // status가 같은 애만 추가해주기
        for(ChallengeParticipant challengeParticipant : challengeParticipantList) {
            if(Objects.equals(statusId, challengeParticipant.getChallenge().getStatus())) {
                challenges.add(challengeParticipant.getChallenge());
            }
        }

        return challenges.stream().map(ChallengeResDto::new).collect(Collectors.toList());
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
                    challengeRespDto.setStatus(challenge.getStatus());

                    return challengeRespDto;
                })
                .collect(Collectors.toList());
    }

    // 챌린지 상세 조회 API
    public ChallengeDetailDto getChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new NotFoundException("해당 챌린지를 찾을 수 없습니다."));

        return new ChallengeDetailDto(challenge, challenge.getChallengeExampleImageList());
    }

    // 챌린지 참여 API
    public ChallengeParticipantDto participateInChallenge(Long challengeId, Long memberId) {
        ChallengeParticipant challengeParticipant = new ChallengeParticipant();

        // 해당 challengeId의 challenge의 팀0 인원과 팀1의 인원 비교
        Integer teamZeroMemberCnt = challengeParticipantRepository.countTeamZeroByChallengeId(challengeId);
        Integer teamOneMemberCnt = challengeParticipantRepository.countTeamOneByChallengeId(challengeId);

        Integer team = 0; // 팀 0으로 일단 배정
        if(teamZeroMemberCnt > teamOneMemberCnt) { // 팀 1의 인원이 더 작을 경우 team1에 사용자 넣어주기
            team = 1;
        }

        challengeParticipant.setTeam(team);
        challengeParticipant.setChallenge(challengeRepository.findById(challengeId)
                .orElseThrow(() -> new NotFoundException("해당 챌린지를 찾을 수 없습니다.")));
        challengeParticipant.setMember(memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 멤버를 찾을 수 없습니다.")));

        // challengeParticipant에 참여자 정보 추가하기
        challengeParticipantRepository.save(challengeParticipant);
        return new ChallengeParticipantDto(challengeParticipant);
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
            // 채팅방 만들기
            ChallengeChattingRoom challengeChattingRoom0 = new ChallengeChattingRoom();
            // 0번팀 채팅방 만들기
            List<ChallengeParticipant> challengeParticipantList0 = challengeParticipantRepository.findByChallengeIdAndTeam(challengeId, 0);
            challengeChattingRoom0.setTeam(0);
            challengeChattingRoom0.setChallenge(challengeRepository.findById(challengeId)
                    .orElseThrow(() -> new NotFoundException("챌린지 없음")));
            for (ChallengeParticipant participant : challengeParticipantList0) {
                if (participant.getTeam() == 0) {
                    challengeChattingRoom0.getRoomParticipantIds().add(participant.getId());
                }
            }
            // 메시지들 연결 필요
            challengeChattingRoomRespository.save(challengeChattingRoom0);

            ChallengeChattingRoom challengeChattingRoom1 = new ChallengeChattingRoom();
            List<ChallengeParticipant> challengeParticipantList1 = challengeParticipantRepository.findByChallengeIdAndTeam(challengeId, 1);
            challengeChattingRoom1.setTeam(1);
            challengeChattingRoom1.setChallenge(challengeRepository.findById(challengeId)
                    .orElseThrow(() -> new NotFoundException("챌린지 없음")));
            for (ChallengeParticipant participant : challengeParticipantList1) {
                if (participant.getTeam() == 1) {
                    challengeChattingRoom1.getRoomParticipantIds().add(participant.getId());
                }
            }
            // 메시지들 연결 필요
            challengeChattingRoomRespository.save(challengeChattingRoom1);

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
